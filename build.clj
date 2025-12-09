(ns build
  (:require [clojure.tools.build.api :as b]))

(def target_directory "target")
(def sources_directory "source")
(def resources_directory "resources")

(def packade_name 'software.horus/application)
(def package_version "0.0.0")

(def package_classes_directory (format "%s/classes" target_directory))

(def package_file (format "%s/%s-%s.jar" target_directory (name packade_name) package_version))

(def basis (delay (b/create-basis {:project "deps.edn"})))

(defn clean [_]
  (println "[ clean ] cleaning previous artifacts")

  (println (format "|- [ delete ] removing '%s' directory" target_directory))

  (b/delete {:path target_directory}))

#_{:clojure-lsp/ignore [:clojure-lsp/unused-public-var]}
(defn package [_]
  (clean nil)

  (println "[ package ] packaging artifacts")

  (println (format "|- [ copy ] copying '%s' to '%s'" resources_directory package_classes_directory))

  (b/copy-dir {:src-dirs [resources_directory]
               :target-dir package_classes_directory})

  (println (format "|- [ compile ] compiling form '%s' to '%s'" sources_directory package_classes_directory))

  (b/compile-clj {:basis @basis
                  :src-dirs [sources_directory]
                  :class-dir package_classes_directory})

  (println (format "|- [ uber ] creating '%s' package" package_file))

  (b/uber {:class-dir package_classes_directory
           :uber-file package_file
           :basis @basis
           :main 'entrypoint}))
