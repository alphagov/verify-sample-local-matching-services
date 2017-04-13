(defproject yada-lms "0.1.0-SNAPSHOT"
  :description "Local Matching Service example in Clojure using Yada"
  :main yada-lms.core
  :profiles {:uberjar {:aot :all}}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [yada "1.2.1"]
                 [js-ps "0.1.4"]
                 [cheshire "5.7.0"]
                 [com.taoensso/timbre "4.8.0"]])
