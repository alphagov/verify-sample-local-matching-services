(ns yada-lms.core
  (:require
    [yada.yada :as yada]
    [schema.core :as s]
    [js-ps.core :refer [->prismatic]]
    [cheshire.core :as cheshire]
    [clojure.java.io :as io]
    [clojure.pprint :refer [pprint]]))

; simple atom for exposing a global function so the server can close itself
(def closer (atom nil))

(def schema-file (io/file (io/resource "matching-request-schema.json")))

(def matching-request-schema (-> schema-file slurp (cheshire/parse-string true) ->prismatic))

(defn cycle3-match [matching-request]
  (= "goodvalue" (get-in matching-request [:cycle3Dataset :attributes :nino])))

(defn cycle01-match [matching-request]
  (= "Griffin" (get-in matching-request [:matchingDataset :surnames 0 :value])))

(defn matching-service-fn [ctx]
  (let [matching-request (get-in ctx [:parameters :body])]
    (cond
      (not= "LEVEL_2" (:levelOfAssurance matching-request)) {:result "failure"}
      (cycle01-match matching-request) {:result "match"}
      (cycle3-match matching-request) {:result "match"}
      :else {:result "no-match"})))

(defn account-creation-fn [ctx]
  (let [matching-request (get-in ctx [:parameters :body])
        loa (:levelOfAssurance matching-request)]
    (cond
      (not= "LEVEL_2" (:levelOfAssurance matching-request)) {:result "failure"}
      (= "failurePid" (:hashedPid matching-request)) {:result "failure"}
      :else {:result "success"})))

(defn as-json-post-resource [on-post-fn]
  (yada/resource
    {:methods {:post {:parameters {:body s/Any}             ;schema not yet working
                      :consumes   "application/json"
                      :produces   "application/json"
                      :response   on-post-fn}}}))

(defn the-routes []
  ["/"
   {
    "healthcheck" (yada/as-resource {:alive :true})
    "matching-service" (as-json-post-resource matching-service-fn)
    "account-creation" (as-json-post-resource account-creation-fn)
    "die"              (yada/resource
                         {:methods
                          {:delete
                           {:produces "text/plain"
                            :response (fn [_]
                                        (future (Thread/sleep 20) (@closer))
                                        "shutting down clojure server.")}}})}])

(defn run-server-returning-promise []
  (let [listener (yada/listener (the-routes) {:port 50139})
        done-promise (promise)]
    (reset! closer (fn []
                     ((:close listener))
                     (deliver done-promise :done)
                     ))
    done-promise))

(defn -main
  [& args]
  (let [done (run-server-returning-promise)]
    (println "clojure server running on port 50139... DELETE \"http://localhost/die\" to kill")
    @done))

(comment "to run in a repl, eval this:"
         (def server-promise (run-server-returning-promise))
         "(you can deref this promise to wait for server exit)"
         "then close it with the DELETE hook above,"
         "or close it by calling the closer:"
         (@closer))
