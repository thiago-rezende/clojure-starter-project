(ns core
  (:require [clojure.java.io :as io])
  (:gen-class))

(def version "0.1.0")

(def resource-path "config.json")
(def resource-content (->
                       "config.json"
                       io/resource
                       io/reader
                       slurp))

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main [& args]
  (println (format "[ application ] v%s" version))
  (println (format "[ resource ] <path:%s>\n%s" resource-path resource-content)))
