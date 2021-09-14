(ns pl.tomaszgigiel.utils.misc
  (:require [clojure.java.io :as io])
  (:require [clojure.set :as set])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:import freemarker.template.Configuration)
  (:import freemarker.template.Template)
  (:import freemarker.template.TemplateExceptionHandler)
  (:import freemarker.template.Version)
  (:import java.io.File)
  (:import java.nio.file.Files)
  (:import java.util.Base64)
  (:gen-class))

(defn long-str [& strings] (str/join "\n" strings))

(defn flatten-one-level [coll] (apply concat coll))

(defn file-create [path content]
  (spit path content :append false))

(defn file-append [path content]
  (spit path content :append true))

(defn freemarker-cfg [template-directory]
  (doto (Configuration. Configuration/VERSION_2_3_31)
    (.setDirectoryForTemplateLoading (new File template-directory))
    ;(.setClassLoaderForTemplateLoading (.getContextClassLoader (Thread/currentThread)) "/templates")
    (.setDefaultEncoding "UTF-8")
    (.setTemplateExceptionHandler TemplateExceptionHandler/RETHROW_HANDLER)
    (.setLogTemplateExceptions false)
    (.setWrapUncheckedExceptions true)
    (.setFallbackOnNullLoopVariable false)))

; https://clojuredocs.org/clojure.java.io/input-stream
(defn file->bytes [x]
  (with-open [in (io/input-stream x)
              out (java.io.ByteArrayOutputStream.)]
    (io/copy in out)
    (.toByteArray out)))

(defn file->base64-string [x] (.encodeToString (Base64/getEncoder) (file->bytes x)))

(defn mimetype [file] (Files/probeContentType (.toPath file)))
