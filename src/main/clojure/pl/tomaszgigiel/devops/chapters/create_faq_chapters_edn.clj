(ns pl.tomaszgigiel.devops.chapters.create-faq-chapters-edn
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.pprint :as pp])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.devops.common :as common])
  (:gen-class))

(defn create-file [path content]
  (spit path content :append false))

(defn chunk-to-item [chunk name]
  (let [grups (str/split chunk #"\r\n\r\n")
        q (str/split-lines (nth grups 0))
        a (str/split-lines (nth grups 1))
        s (str/split-lines (nth grups 2))
        t [(str/replace name #".txt$" "")]
        c [chunk]]
    {:question q :answer a :source s :tag t :chunk c}))

(defn file-to-chunks [file]
  (let [DO-NOT-USE-THIS-STRING "\r\n DO-NOT-USE-THIS-STRING \r\n"
        content (slurp file)
        content (str/replace content #"\r\n.\r\n" DO-NOT-USE-THIS-STRING)
        content (str/replace content #"^.\r\n" DO-NOT-USE-THIS-STRING)
        chunks (str/split content (re-pattern DO-NOT-USE-THIS-STRING))
        chunks (map str/trim chunks)
        chunks (remove str/blank? chunks)]
    chunks))

(defn file-to-items [file]
  (let [name (.getName file)
        chunks (file-to-chunks file)
        items (map #(chunk-to-item % name) chunks)]
    items))

(defn create-edn [props]
  (let [dir (:faq-chapters-path props)
        dir (io/file dir)
        files (sort (filter #(.isFile %) (file-seq dir)))
        items (map file-to-items files)
        items (common/flatten-one-level items)
        content (vec items)
        content  (with-out-str (pp/pprint content))
        out (:faq-chapters-edn-path props)]
    (create-file out content)))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (create-edn props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.chapters.create-faq-chapters-edn: ok")
      (shutdown-agents)))
