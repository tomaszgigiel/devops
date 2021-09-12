(ns pl.tomaszgigiel.devops.chapters.create-faq-chapters-freemarker-wiki
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.pprint :as pp])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.utils.misc :as misc])
  (:gen-class))

(defn create-freemarker [props]
  (let [cfg (misc/freemarker-cfg (:freemarker-template-directory props))
        template (.getTemplate cfg (:freemarker-template-faq-chapters-wiki-filename props))
        model (edn/read-string (slurp (:faq-chapters-edn-path props)))
        out (:faq-chapters-freemarker-wiki-path props)
        content (with-out-str (.process template model *out*))]
    (misc/file-create out content)))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (create-freemarker props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.chapters.create-faq-chapters-freemarker-wiki: ok")
      (shutdown-agents)))
