(ns yada-lms.core
  (:require
    [yada.yada :as yada]
    [schema.core :as s]
    [js-ps.core :refer [->prismatic]]
    [cheshire.core :as cheshire]
    [clojure.java.io :as io]
    [clojure.pprint :refer [pprint]]))

(def schema-file (io/file (io/resource "matching-request-schema.json")))

(def matching-request-schema (-> schema-file slurp (cheshire/parse-string true) ->prismatic))

(defn cycle01-match [matching-request]
  (= "Griffin" (get-in matching-request [:matchingDataset :surnames 0 :value])))

(defn cycle3-match [matching-request]
  (= "goodvalue" (get-in matching-request [:cycle3Dataset :attributes :nino])))

(defn matching-service-fn
  "Given a request context, return an appropriate matching service result"
  [ctx]
  (let [matching-request (get-in ctx [:parameters :body])]
    (cond
      (not= "LEVEL_2" (:levelOfAssurance matching-request)) {:result "no-match"} ;should this be "failure"?
      (cycle01-match matching-request) {:result "match"}
      (cycle3-match matching-request) {:result "match"}
      :else {:result "no-match"})))

(defn account-creation-fn
  "Given a request context, return an appropriate account creation result"
  [ctx]
  (let [matching-request (get-in ctx [:parameters :body])]
    (cond
      (not= "LEVEL_2" (:levelOfAssurance matching-request)) {:result "failure"}
      (= "failurePid" (:hashedPid matching-request)) {:result "failure"}
      :else {:result "success"})))

(defn as-json-post-resource
  "wrap a simple function for handling requests, as a resource that takes a simple JSON POST and returns JSON
  This will also validate matching request schemas once I get that working"
  [on-post-fn]
  (yada/resource
    {:methods {:post {:parameters {:body s/Any}             ;schema not yet working
                      :consumes   "application/json"
                      :produces   "application/json"
                      :response   on-post-fn}}}))

(def web-server-closer
  "this is storage for a function to close the current web server -
  wrapped in an atom so it is globally accessible and settable"
  (atom nil))

(defn routes "returns http routes and their Yada resources" []
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
                                        (future (Thread/sleep 20) (@web-server-closer))
                                        "shutting down clojure server.")}}})}])

(defn run-server-returning-promise []
  (let [listener (yada/listener (routes) {:port 50139})
        done-promise (promise)]
    (reset! web-server-closer (fn []
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
         "or close it by calling the web-server-closer:"
         (@web-server-closer))
