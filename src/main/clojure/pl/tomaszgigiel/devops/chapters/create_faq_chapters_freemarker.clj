(ns pl.tomaszgigiel.devops.chapters.create-faq-chapters-freemarker
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

(defn freemarker-cfg [template-directory]
  (doto (Configuration. Configuration/VERSION_2_3_31)
    ;(.setDirectoryForTemplateLoading (new File template-directory)) ;TGL:
    (.setClassLoaderForTemplateLoading (.getContextClassLoader (Thread/currentThread)) template-directory)
    (.setDefaultEncoding "UTF-8")
    (.setTemplateExceptionHandler TemplateExceptionHandler/RETHROW_HANDLER)
    (.setLogTemplateExceptions false)
    (.setWrapUncheckedExceptions true)
    (.setFallbackOnNullLoopVariable false)))

(defn create-freemarker [props]
  (let [template-directory (:faq-chapters-freemarker-template-directory props)
        template-filename (:faq-chapters-freemarker-template-filename props)
        in (:faq-chapters-edn-path props)
        out (:faq-chapters-freemarker-path props)
        cfg (freemarker-cfg template-directory)
        template (.getTemplate cfg template-filename)

        ;; TGL:
        model {"items" [{"question" ["my-question1"], "answer" ["my-answer1:" "- line1" "- line2" "foo:" "- line-foo1"], "source" ["my-source1"], "tag" ["my-tag1"],  "chunk" ["my-chunk1"]}
                        {"question" ["my-question2"], "answer" ["my-answer2" "my-answer21"], "source" ["my-source2"], "tag" ["my-tag2"],  "chunk" ["my-chunk2"]}]}

        ]
    (.process template model *out*)
    (println "ok!")))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (create-freemarker props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.chapters.create-faq-chapters-freemarker: ok")
      (shutdown-agents)))
