(ns pl.tomaszgigiel.devops.tiddlywiki.prepare-tiddlywiki
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.pprint :as pp])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.utils.misc :as misc])
  (:gen-class))

(def TITLE "DevOps. Światowej klasy zwinność, niezawodność i bezpieczeństwo w Twojej organizacji")
(def SUBTITLE "Notatki z lektury")

(defn tiddlywiki-init [tiddlywiki title subtitle]
  (let [tiddlywiki (str/replace-first tiddlywiki (re-pattern "<title>.*</title>") (str "<title>" title " — " subtitle "</title>"))
        tiddlywiki (str/replace-first tiddlywiki "<div class=\" tc-reveal\" hidden=\"true\"></div>" (misc/long-str "<p>This <a class=\"tc-tiddlylink-external\" href=\"https://tiddlywiki.com\" rel=\"noopener noreferrer\" target=\"_blank\">TiddlyWiki</a> contains the following tiddlers:</p><p><ul>"
                                                                                                                   "<li>$:/config/PageControlButtons/Visibility/$:/core/ui/Buttons/home</li>";;;
                                                                                                                   "<li>$:/core</li>"
                                                                                                                   "<li>$:/DefaultTiddlers</li>"
                                                                                                                   "<li>$:/isEncrypted</li>"
                                                                                                                   "<li>$:/SiteSubtitle</li>"
                                                                                                                   "<li>$:/SiteTitle</li>"
                                                                                                                   "<li>$:/state/tab-1749438307</li>"
                                                                                                                   "<li>$:/state/tab/moresidebar-1850697562</li>"
                                                                                                                   "<li>$:/state/tab/sidebar--595412856</li>"
                                                                                                                   "<li>$:/status/RequireReloadDueToPluginChange</li>"
                                                                                                                   "<li>$:/StoryList</li>"
                                                                                                                   "<li>$:/themes/tiddlywiki/snowwhite</li>"
                                                                                                                   "<li>$:/themes/tiddlywiki/vanilla</li>"
                                                                                                                   "<li>Lista wpisów</li>"
                                                                                                                   "<li>Spis treści</li>"
                                                                                                                   "</ul>"
                                                                                                                   "</p>"))
        emphasises-individual-tiddlers (re-find (re-matcher (re-pattern "\n.*description=\"Emphasises individual tiddlers\".*\n") tiddlywiki))
        tiddlywiki (str/replace-first tiddlywiki emphasises-individual-tiddlers (misc/long-str "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/SiteSubtitle\">"
                                                                                               (str "<pre>" subtitle "</pre>")
                                                                                               "</div>"
                                                                                               ;
                                                                                               "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/SiteTitle\">"
                                                                                               (str "<pre>" title "</pre>")
                                                                                               "</div>"
                                                                                               ;
                                                                                               "<div title=\"$:/status/RequireReloadDueToPluginChange\">"
                                                                                               "<pre>no</pre>"
                                                                                               "</div>"
                                                                                               ;
                                                                                               "<div list=\"[[Spis treści]]\" title=\"$:/StoryList\">"
                                                                                               "<pre></pre>"
                                                                                               "</div>"
                                                                                               ;
                                                                                               "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/DefaultTiddlers\">"
                                                                                               "<pre>&quot;Spis treści&quot;</pre>"
                                                                                               "</div>"
                                                                                               ;
                                                                                               "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/state/tab-1749438307\">"
                                                                                               "<pre>$:/core/ui/ControlPanel/Info</pre>"
                                                                                               "</div>"
                                                                                               ;
                                                                                               "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/state/tab/moresidebar-1850697562\">"
                                                                                               "<pre>$:/core/ui/MoreSideBar/Recent</pre>"
                                                                                               "</div>"
                                                                                               ;
                                                                                               "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/state/tab/sidebar--595412856\">"
                                                                                               "<pre>$:/core/ui/SideBar/Open</pre>"
                                                                                               "</div>"
                                                                                               ;
                                                                                               emphasises-individual-tiddlers))

        basic-theme (re-find (re-matcher (re-pattern "\n.*description=\"Basic theme\".*\n<pre>(?s).*</pre>\n</div>") tiddlywiki))
        tiddlywiki (str/replace-first tiddlywiki basic-theme (misc/long-str basic-theme
                                                                            "<div created=\"20210101000000001\" modified=\"20210101000000001\" tags=\"$:/tags/SideBar\" title=\"Lista wpisów\" type=\"text/vnd.tiddlywiki\">"
                                                                            "<pre>&lt;$list filter={{$:/core/Filters/AllTiddlers!!filter}} template=&quot;$:/core/ui/ListItemTemplate&quot;/&gt;</pre>"
                                                                            "</div>"
                                                                            ;
                                                                            "<div created=\"20210101000000001\" modified=\"20210101000000001\" tags=\"$:/tags/SideBar\" title=\"Spis treści\" type=\"text/vnd.tiddlywiki\">"
                                                                            "<pre>&lt;$list filter=&quot;[tag[toc]] +[nsort[page]]&quot; template=&quot;$:/core/ui/ListItemTemplate&quot;/&gt;</pre>"
                                                                            "</div>"
                                                                            ))

        store-area "<div id=\"storeArea\" style=\"display:none;\">"
        tiddlywiki (str/replace-first tiddlywiki store-area (misc/long-str store-area
                                                                           "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/config/PageControlButtons/Visibility/$:/core/ui/Buttons/home\">"
                                                                           "<pre>show</pre>"
                                                                           "</div>"))]
    tiddlywiki))

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

(defn prepare-tiddlywiki [props]
  (let [tiddlywiki (slurp (:tiddlywiki-empty-path props) :encoding  "UTF-8")
        tiddlywiki-result-path (:tiddlywiki-result-path props)
        tiddlywiki (tiddlywiki-init tiddlywiki TITLE SUBTITLE)
        tiddlywiki (tiddlywiki-add-tiddler tiddlywiki "Na czym opierają się niniejsze notatki?" "Niniejsze notatki opierają się na:\n&quot;DevOps. Światowej klasy zwinność, niezawodność i bezpieczeństwo w Twojej organizacji&quot;,\nGene Kim,\nPatrick Debois,\nJohn Willis,\nJez Humble,\nJohn Allspaw" "chapter=\"devops-handbook-01-preface\" page=\"7\" tags=\"DevOps-7 devops-handbook-01-preface toc\"")
        tiddlywiki (tiddlywiki-add-tiddler tiddlywiki "Co to jest SLA?" "SLA (service level agreement) to:\n- umowa zapewnienia poziomu usług" "chapter=\"devops-handbook-01-preface\" page=\"8\" tags=\"DevOps-8 devops-handbook-01-preface toc\"")
        ]
    (misc/file-create tiddlywiki-result-path tiddlywiki)))

(defn- work [path]
  (let [props (with-open [r (io/reader path)] (edn/read (java.io.PushbackReader. r)))]
    (prepare-tiddlywiki props)))

(defn -main [& args]
  "devops: DevOps Notes"
    (let [{:keys [uri options exit-message ok?]} (cmd/validate-args args)]
      (if exit-message
        (cmd/exit (if ok? 0 1) exit-message)
        (work (first args)))
      (log/info "pl.tomaszgigiel.devops.tiddlywiki.prepare-tiddlywiki: ok")
      (shutdown-agents)))
