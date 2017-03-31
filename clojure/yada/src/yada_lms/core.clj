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

(defn matching-service-fn [ctx]
  (let [body (get-in ctx [:parameters :body])]
    (cond
      (not= "LEVEL_2" (:levelOfAssurance body)) {:result "no-match"}
      (= "badvalue" (get-in body [:cycle3Dataset :attributes :nino])) {:result "no-match"}
      (= "Griffin" (get-in body [:matchingDataset :surnames 0 :value])) {:result "no-match"}
      :else {:result "match"}
      )
    )
  )

(defn account-creation-fn [ctx]
  (let [body (get-in ctx [:parameters :body])
        loa (:levelOfAssurance body)]
    (cond
      (= "failurePid" (:hashedPid body)) {:result "failure"}
      :else {:result "success"}
      )
    )
  )

(defn as-json-post-resource [on-post-fn]
  (yada/resource
    {:methods {:post {:parameters {:body s/Any}
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
                                        "shutting down server.")}}})}])

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
    (println "server running on port 50139... DELETE \"http://localhost/die\" to kill")
    @done))

(comment "to run in a repl, eval this:"
         (def server-promise (run-server-returning-promise))
         "then either wait on the promise:"
         @server-promise
         "or with a timeout"
         (deref server-promise 1000 :timeout)
         "or close it yourself"
         (@closer)
         )
