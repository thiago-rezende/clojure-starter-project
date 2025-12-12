(ns build
  (:require [clojure.tools.build.api :as build]))

(def target-directory "target")
(def source-directory "source")
(def resources-directory "resources")

(def package-name 'software.horus/application)
(def package-version "0.0.0")

(def package-entrypoint 'entrypoint)
(def package-classes-directory (format "%s/classes" target-directory))

(def package-file (format "%s/%s-%s.jar" target-directory (name package-name) package-version))

(def basis (delay (build/create-basis {:project "deps.edn"})))

(defn clean [_]
  (println "[ clean ] cleaning previous artifacts")

  (println (format "|- [ delete ] removing '%s' directory" target-directory))

  (build/delete {:path target-directory}))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn package [_]
  (clean nil)

  (println "[ package ] packaging artifacts")

  (println (format "|- [ copy ] copying '%s' to '%s'" resources-directory package-classes-directory))

  (build/copy-dir {:src-dirs [resources-directory]
                   :target-dir package-classes-directory})

  (println (format "|- [ compile ] compiling form '%s' to '%s'" source-directory package-classes-directory))

  (build/compile-clj {:basis @basis
                      :src-dirs [source-directory]
                      :class-dir package-classes-directory})

  (println (format "|- [ uber ] creating '%s' package" package-file))

  (build/uber {:main package-entrypoint
               :basis @basis
               :uber-file package-file
               :class-dir package-classes-directory}))
