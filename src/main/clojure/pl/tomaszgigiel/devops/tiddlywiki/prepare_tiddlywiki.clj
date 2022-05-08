(ns pl.tomaszgigiel.devops.tiddlywiki.prepare-tiddlywiki
  (:require [clojure.edn :as edn])
  (:require [clojure.java.io :as io])
  (:require [clojure.pprint :as pp])
  (:require [clojure.string :as str])
  (:require [clojure.tools.logging :as log])
  (:require [pl.tomaszgigiel.devops.cmd :as cmd])
  (:require [pl.tomaszgigiel.utils.misc :as misc])
  (:gen-class))

(defn prepare [tiddlywiki]
  (let [title-subtitle "<title>TiddlyWiki — a non-linear personal web notebook</title>"
        tiddlywiki (str/replace-first tiddlywiki title-subtitle (str "<!-- ARTILLERY-TAG-TITLE-SUBTITLE-START -->" title-subtitle "<!-- ARTILLERY-TAG-TITLE-SUBTITLE-END -->"))

        reveal "<div class=\" tc-reveal\" hidden=\"true\"></div>"
        tiddlywiki (str/replace-first tiddlywiki reveal (misc/long-str
                                                          "<!-- ARTILLERY-TAG-ENUMERATION-START -->"
                                                          "<p>This <a class=\"tc-tiddlylink-external\" href=\"https://tiddlywiki.com\" rel=\"noopener noreferrer\" target=\"_blank\">TiddlyWiki</a> contains the following tiddlers:</p><p><ul>"
                                                          "<li>$:/config/PageControlButtons/Visibility/$:/core/ui/Buttons/home</li>"
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
                                                          (str "<!-- ARTILLERY-TAG-LIST-OF-ENTRIES-LI-START -->" "<li>List of Entries</li>" "<!-- ARTILLERY-TAG-LIST-OF-ENTRIES-LI-END -->")
                                                          (str "<!-- ARTILLERY-TAG-TABLE-OF-CONTENTS-LI-START -->" "<li>Table of Contents</li>" "<!-- ARTILLERY-TAG-TABLE-OF-CONTENTS-LI-END -->")
                                                          "<!-- ARTILLERY-TAG-LIST-OF-TIDDLERS-LI-START -->"
                                                          "<!-- ARTILLERY-TAG-LIST-OF-TIDDLERS-LI-END -->"
                                                          "</ul>"
                                                          "</p>"
                                                          "<!-- ARTILLERY-TAG-ENUMERATION-END -->"))
        
        store-area "<div id=\"storeArea\" style=\"display:none;\">"
        tiddlywiki (str/replace-first tiddlywiki store-area (misc/long-str store-area
                                                                           "<!-- ARTILLERY-TAG-STORE-AREA-START -->"
                                                                           "<!-- ARTILLERY-TAG-LIST-OF-ENTRIES-TITLE-START -->"
                                                                           (str "<div created=\"20210101000000001\" modified=\"20210101000000001\" tags=\"$:/tags/SideBar\" title=\"List of Entries\" type=\"text/vnd.tiddlywiki\">")
                                                                           "<pre>&lt;$list filter={{$:/core/Filters/AllTiddlers!!filter}} template=&quot;$:/core/ui/ListItemTemplate&quot;/&gt;</pre>"
                                                                           "</div>"
                                                                           "<!-- ARTILLERY-TAG-LIST-OF-ENTRIES-TITLE-END -->"
                                                                           ;
                                                                           "<!-- ARTILLERY-TAG-TABLE-OF-CONTENTS-TITLE-START -->"
                                                                           (str "<div created=\"20210101000000001\" modified=\"20210101000000001\" tags=\"$:/tags/SideBar\" title=\"Table of Contents\" type=\"text/vnd.tiddlywiki\">")
                                                                           "<pre>&lt;$list filter=&quot;[tag[toc]] +[nsort[order]]&quot; template=&quot;$:/core/ui/ListItemTemplate&quot;/&gt;</pre>"
                                                                           "</div>"
                                                                           "<!-- ARTILLERY-TAG-TABLE-OF-CONTENTS-TITLE-END -->"
                                                                           ;
                                                                           "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/config/PageControlButtons/Visibility/$:/core/ui/Buttons/home\">"
                                                                           "<pre>show</pre>"
                                                                           "</div>"
                                                                           ;
                                                                           "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/DefaultTiddlers\">"
                                                                           (str "<!-- ARTILLERY-TAG-TABLE-OF-CONTENTS-PRE-START -->" "<pre>&quot;Table of Contents&quot;</pre>" "<!-- ARTILLERY-TAG-TABLE-OF-CONTENTS-PRE-END -->")
                                                                           "</div>"
                                                                           ;
                                                                           "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/SiteSubtitle\">"
                                                                           (str "<!-- ARTILLERY-TAG-SUBTITLE-PRE-START -->" "<pre>a non-linear personal web notebook</pre>" "<!-- ARTILLERY-TAG-SUBTITLE-PRE-END -->")
                                                                           "</div>"
                                                                           ;
                                                                           "<div created=\"20210101000000001\" modified=\"20210101000000001\" title=\"$:/SiteTitle\">"
                                                                           (str "<!-- ARTILLERY-TAG-TITLE-PRE-START -->" "<pre>TiddlyWiki</pre>" "<!-- ARTILLERY-TAG-TITLE-PRE-END -->")
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
                                                                           "<div title=\"$:/status/RequireReloadDueToPluginChange\">"
                                                                           "<pre>no</pre>"
                                                                           "</div>"
                                                                           ;
                                                                           "<div>" "<!-- ARTILLERY-TAG-TABLE-OF-CONTENTS-LIST-START -->" " list=\"[[Table of Contents]]\" " "<!-- ARTILLERY-TAG-TABLE-OF-CONTENTS-LIST-END -->" " title=\"$:/StoryList\">"
                                                                           "<pre></pre>"
                                                                           "</div>"
                                                                           "<!-- ARTILLERY-TAG-STORE-AREA-END -->"))]

    tiddlywiki))

(defn set-title-subtitle [tiddlywiki title subtitle]
  (let [title-subtitle (re-pattern "<!-- ARTILLERY-TAG-TITLE-SUBTITLE-START -->.*<!-- ARTILLERY-TAG-TITLE-SUBTITLE-END -->")
        tiddlywiki (str/replace tiddlywiki title-subtitle (str "<!-- ARTILLERY-TAG-TITLE-SUBTITLE-START -->" "<title>" title " — " subtitle "</title>" "<!-- ARTILLERY-TAG-TITLE-SUBTITLE-END -->"))

        title-only (re-pattern "<!-- ARTILLERY-TAG-TITLE-PRE-START -->.*<!-- ARTILLERY-TAG-TITLE-PRE-END -->")
        tiddlywiki (str/replace tiddlywiki title-only (str "<!-- ARTILLERY-TAG-TITLE-PRE-START -->" "<pre>" title "</pre>" "<!-- ARTILLERY-TAG-TITLE-PRE-END -->"))

        subtitle-only (re-pattern "<!-- ARTILLERY-TAG-SUBTITLE-PRE-START -->.*<!-- ARTILLERY-TAG-SUBTITLE-PRE-END -->")
        tiddlywiki (str/replace tiddlywiki subtitle-only (str "<!-- ARTILLERY-TAG-SUBTITLE-PRE-START -->" "<pre>" subtitle "</pre>" "<!-- ARTILLERY-TAG-SUBTITLE-PRE-END -->"))]
    tiddlywiki))

(defn prepare-tiddlywiki [props]
  (let [tiddlywiki (slurp (:tiddlywiki-empty-path props) :encoding  "UTF-8")
        tiddlywiki-result-path (:tiddlywiki-result-path props)
        tiddlywiki (prepare tiddlywiki)
        tiddlywiki (set-title-subtitle tiddlywiki (:tiddlywiki-title props) (:tiddlywiki-subtitle props))]
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
