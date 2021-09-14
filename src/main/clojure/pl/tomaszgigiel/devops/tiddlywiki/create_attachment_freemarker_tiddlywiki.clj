(ns pl.tomaszgigiel.devops.tiddlywiki.create-attachment-freemarker-tiddlywiki
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
        title (:tiddlywiki-attachment-title props)
        file (io/file (:attachment-path props))
        content (slurp file :encoding  "UTF-8")
        tags "attachment"
        ;mimetype (misc/mimetype file)
        mimetype "text/plain"
        model {"items" [{"title" title "content" content "tags" tags "mimetype" mimetype}]}
        out (:tiddlywiki-result-path props)
        tiddlywiki (slurp out :encoding  "UTF-8")

        template (.getTemplate cfg (:freemarker-template-attachment-tiddlywiki-list props))
        content (with-out-str (.process template model *out*))
        tiddlywiki (str/replace-first tiddlywiki "<!-- ARTILLERY-TAG-LIST-OF-TIDDLERS-LI-END -->" (str content "<!-- ARTILLERY-TAG-LIST-OF-TIDDLERS-LI-END -->"))

        template (.getTemplate cfg (:freemarker-template-attachment-tiddlywiki-tiddlers props))
        content (with-out-str (.process template model *out*))
        tiddlywiki (str/replace-first tiddlywiki "<!-- ARTILLERY-TAG-STORE-AREA-END -->" (str content "<!-- ARTILLERY-TAG-STORE-AREA-END -->"))]
    (misc/file-create out tiddlywiki)))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (create-freemarker props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.tiddlywiki.create-attachment-freemarker-tiddlywiki: ok")
      (shutdown-agents)))
