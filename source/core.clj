(ns core
  (:gen-class))

(def version "0.1.0")

#_{:clj-kondo/ignore [:unused-binding]}
(defn -main [& args]
  (println (format "[ application ] v%s" version)))
