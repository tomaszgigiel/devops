(ns pl.tomaszgigiel.devops.chapters.asciidoctor
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.devops.common :as common])
  (:import java.util.HashMap)
  (:import org.asciidoctor.Asciidoctor$Factory)
  (:gen-class))

(defn create-file [path content]
  (spit path content :append false))

(defn asciidoctor-files [props]
  (let [in (:faq-chapters-merged-path props)
        content (slurp in)
        out (:faq-chapters-merged-asciidoctor-path props)
        content (.. Asciidoctor$Factory (create) (convert content (new HashMap)))]
    (create-file out content)))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (asciidoctor-files props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.chapters.asciidoctor: ok")
      (shutdown-agents)))
