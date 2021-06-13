(ns pl.tomaszgigiel.devops.pre
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.devops.common :as common])
  (:gen-class))

(defn create-file [dir name]
  (spit (str dir name ".txt") ""))

(defn create-files [props]
  (let [dir (:faq-chapters-path props)
        lines (remove str/blank? (str/split-lines (slurp (:chapters-path props))))]
    (run! #(create-file dir %) lines)))

; ok: watch out, run only once
(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (create-files props)))

; ok: watch out, for safety
(defn- work [path])

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.pre: ok")
      (shutdown-agents)))
