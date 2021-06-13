(ns pl.tomaszgigiel.devops.core
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.devops.common :as common])
  (:gen-class))

(defn create-dirs [props]
  (log/info props))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (create-dirs props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "ok")
      (shutdown-agents)))
