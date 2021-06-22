(ns pl.tomaszgigiel.devops.chapters.merge
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.devops.common :as common])
  (:gen-class))

(defn create-file [path content]
  (spit path content :append false))

(defn merge-files [props]
  (let [dir (:faq-chapters-path props)
        dir (io/file dir)
        files (sort (filter #(.isFile %) (file-seq dir)))
        content (mapv slurp files)
        content (apply str content)
        out (:faq-chapters-merged-path props)]
    (create-file out content)))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (merge-files props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.chapters.merge: ok")
      (shutdown-agents)))
