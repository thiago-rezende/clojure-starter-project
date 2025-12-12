(ns entrypoint
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

(def version "0.0.0")

(def resource-path "config.json")
(def resource-content (->
                       "config.json"
                       io/resource
                       io/reader
                       slurp))

(defn prefix-lines [prefix content]
  (->> content
       (string/split-lines)
       (map #(str prefix %))
       (string/join "\n")))

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main [& args]
  (println (format "[ application ] v%s" version))
  (println (format "|- [ resource ] <path:%s>\n%s" resource-path (prefix-lines "|- |" resource-content))))
