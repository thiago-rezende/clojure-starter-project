(ns entrypoint
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def version "0.2.1")

(def resource-path "config.edn")
(def resource-content (slurp (io/resource resource-path)))

(defn prefix-lines [prefix content]
  (->> content
       (string/split-lines)
       (map #(str prefix %))
       (string/join "\n")))

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main [& args]
  (println (format "[ application ] v%s" version))
  (println (format "|- [ resource ] <path:%s>\n%s" resource-path (prefix-lines "|- |" resource-content))))
