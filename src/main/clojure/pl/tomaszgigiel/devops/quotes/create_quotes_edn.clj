(ns pl.tomaszgigiel.devops.quotes.create-quotes-edn
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.pprint :as pp])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.utils.misc :as misc])
  (:gen-class))

(defn chunk-to-item [chunk name]
  (let [q chunk
        t [(str/replace name #".txt$" "")]]
    {"quote" q "tag" t}))

(defn file-to-chunks [file]
  (let [content (slurp file)
        chunks (str/split-lines content)
        chunks (map str/trim chunks)
        chunks (remove str/blank? chunks)]
    chunks))

(defn file-to-items [file]
  (let [name (.getName file)
        chunks (file-to-chunks file)
        items (map #(chunk-to-item % name) chunks)]
        items))

(defn create-edn [props]
  (let [file (io/file (:quotes-path props))
        items (file-to-items file)
        items (map-indexed #(assoc %2 "index" %1) items)
        content (vec items)
        content {"items" content}
        content  (with-out-str (pp/pprint content))
        out (:quotes-edn-path props)]
    (misc/file-create out content)))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (create-edn props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.quotes.create-quotes-edn: ok")
      (shutdown-agents)))
