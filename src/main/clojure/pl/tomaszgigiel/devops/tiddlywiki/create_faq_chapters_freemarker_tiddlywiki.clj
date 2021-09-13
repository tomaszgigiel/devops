(ns pl.tomaszgigiel.devops.tiddlywiki.create-faq-chapters-freemarker-tiddlywiki
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.pprint :as pp])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.utils.misc :as misc])
  (:gen-class))

(defn tiddlywiki-add-tiddler [tiddlywiki title content header]
  (let [contains-the-following-tiddlers-old (re-find (re-matcher (re-pattern "<ul>(?s).*</ul>") tiddlywiki))
        contains-the-following-tiddlers-new (str/replace-first contains-the-following-tiddlers-old "</ul>" (str "<li>" title "</li>\n" "</ul>"))
        tiddlywiki (str/replace-first tiddlywiki contains-the-following-tiddlers-old contains-the-following-tiddlers-new)

        basic-theme (re-find (re-matcher (re-pattern "\n.*description=\"Basic theme\".*\n<pre>(?s).*</pre>\n</div>") tiddlywiki))
        tiddlywiki (str/replace-first tiddlywiki basic-theme (misc/long-str basic-theme
                                                                            (str "<div created=\"20210101000000001\" modified=\"20210101000000001\" type=\"text/plain\" title=\"" title "\" " header ">")
                                                                            (str "<pre>" content "</pre>")
                                                                            "</div>"))]
    tiddlywiki))


(defn create-freemarker [props]
  (let [cfg (misc/freemarker-cfg (:freemarker-template-directory props))
        model (edn/read-string (slurp (:faq-chapters-edn-path props)))
        out (:tiddlywiki-result-path props)
        tiddlywiki (slurp out :encoding  "UTF-8")

        template (.getTemplate cfg (:freemarker-template-faq-chapters-tiddlywiki-list props))
        content (with-out-str (.process template model *out*))
        tiddlywiki (str/replace-first tiddlywiki "<!-- ARTILLERY-TAG-LIST-OF-TIDDLERS-LI-END -->" (str content "<!-- ARTILLERY-TAG-LIST-OF-TIDDLERS-LI-END -->"))

        template (.getTemplate cfg (:freemarker-template-faq-chapters-tiddlywiki-tiddlers props))
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
      (log/info "pl.tomaszgigiel.devops.tiddlywiki.create-faq-chapters-freemarker-tiddlywiki: ok")
      (shutdown-agents)))
