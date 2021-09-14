(defproject devops "1018"
  :description "devops: DevOps Notes"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.clojure/tools.logging "1.1.0"]
                 [org.asciidoctor/asciidoctorj "2.5.1"]
                 [org.freemarker/freemarker "2.3.31"]]

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources" "src/test/resources"]
  :target-path "target/%s"
  :jar-name "devops.jar"
  :uberjar-name "devops-uberjar.jar"
  :main pl.tomaszgigiel.devops.core
  :aot [pl.tomaszgigiel.devops.core]

  :profiles {:test {:resource-paths ["src/test/resources"]}
             :main-core {:main pl.tomaszgigiel.devops.core}
             :main-create-faq-chapters {:main pl.tomaszgigiel.devops.chapters.create-faq-chapters}
             :main-create-faq-chapters-merged {:main pl.tomaszgigiel.devops.chapters.create-faq-chapters-merged}
             :main-create-faq-chapters-asciidoctor {:main pl.tomaszgigiel.devops.chapters.create-faq-chapters-asciidoctor}
             :main-create-faq-chapters-edn {:main pl.tomaszgigiel.devops.chapters.create-faq-chapters-edn}
             :main-create-faq-chapters-freemarker-wiki {:main pl.tomaszgigiel.devops.chapters.create-faq-chapters-freemarker-wiki}
             :main-create-faq-chapters-freemarker-anki {:main pl.tomaszgigiel.devops.chapters.create-faq-chapters-freemarker-anki}
             :main-create-quotes-edn {:main pl.tomaszgigiel.devops.quotes.create-quotes-edn}
             :main-create-quotes-freemarker {:main pl.tomaszgigiel.devops.quotes.create-quotes-freemarker}

             :main-prepare-tiddlywiki {:main pl.tomaszgigiel.devops.tiddlywiki.prepare-tiddlywiki}
             :main-create-mind-map-freemarker-tiddlywiki {:main pl.tomaszgigiel.devops.tiddlywiki.create-mind-map-freemarker-tiddlywiki}
             :main-create-quotes-freemarker-tiddlywiki {:main pl.tomaszgigiel.devops.tiddlywiki.create-quotes-freemarker-tiddlywiki}
             :main-create-attachment-freemarker-tiddlywiki {:main pl.tomaszgigiel.devops.tiddlywiki.create-attachment-freemarker-tiddlywiki}
             :main-create-faq-chapters-freemarker-tiddlywiki {:main pl.tomaszgigiel.devops.tiddlywiki.create-faq-chapters-freemarker-tiddlywiki}}

  :aliases {"run-main-core" ["with-profile" "main-core" "run"]
            "run-main-create-faq-chapters" ["with-profile" "main-create-faq-chapters" "run"]
            "run-main-create-faq-chapters-merged" ["with-profile" "main-create-faq-chapters-merged" "run"]
            "run-main-create-faq-chapters-asciidoctor" ["with-profile" "main-create-faq-chapters-asciidoctor" "run"]
            "run-main-create-faq-chapters-edn" ["with-profile" "main-create-faq-chapters-edn" "run"]
            "run-main-create-faq-chapters-freemarker-wiki" ["with-profile" "main-create-faq-chapters-freemarker-wiki" "run"]
            "run-main-create-faq-chapters-freemarker-anki" ["with-profile" "main-create-faq-chapters-freemarker-anki" "run"]
            "run-main-create-quotes-edn" ["with-profile" "main-create-quotes-edn" "run"]
            "run-main-create-quotes-freemarker" ["with-profile" "main-create-quotes-freemarker" "run"]

            "run-main-prepare-tiddlywiki" ["with-profile" "main-prepare-tiddlywiki" "run"]
            "run-main-create-mind-map-freemarker-tiddlywiki" ["with-profile" "main-create-mind-map-freemarker-tiddlywiki" "run"]
            "run-main-create-quotes-freemarker-tiddlywiki" ["with-profile" "main-create-quotes-freemarker-tiddlywiki" "run"]
            "run-main-create-attachment-freemarker-tiddlywiki" ["with-profile" "main-create-attachment-freemarker-tiddlywiki" "run"]
            "run-main-create-faq-chapters-freemarker-tiddlywiki" ["with-profile" "main-create-faq-chapters-freemarker-tiddlywiki" "run"]}
)
