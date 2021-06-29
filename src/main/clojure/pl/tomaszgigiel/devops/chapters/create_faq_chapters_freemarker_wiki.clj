(ns pl.tomaszgigiel.devops.chapters.create-faq-chapters-freemarker-wiki
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.pprint :as pp])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.devops.common :as common])
  (:import freemarker.template.Configuration)
  (:import freemarker.template.Template)
  (:import freemarker.template.TemplateExceptionHandler)
  (:import freemarker.template.Version)
  (:import java.io.File)
  (:gen-class))

(defn create-file [path content]
  (spit path content :append false))

(defn freemarker-cfg [template-directory]
  (doto (Configuration. Configuration/VERSION_2_3_31)
    (.setDirectoryForTemplateLoading (new File template-directory))
    ;(.setClassLoaderForTemplateLoading (.getContextClassLoader (Thread/currentThread)) "/templates")
    (.setDefaultEncoding "UTF-8")
    (.setTemplateExceptionHandler TemplateExceptionHandler/RETHROW_HANDLER)
    (.setLogTemplateExceptions false)
    (.setWrapUncheckedExceptions true)
    (.setFallbackOnNullLoopVariable false)))

(defn create-freemarker [props]
  (let [cfg (freemarker-cfg (:freemarker-template-directory props))
        template (.getTemplate cfg (:freemarker-template-faq-chapters-wiki-filename props))
        model (edn/read-string (slurp (:faq-chapters-edn-path props)))
        out (:faq-chapters-freemarker-wiki-path props)
        content (with-out-str (.process template model *out*))]
    (create-file out content)))

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
