(defproject devops "1006"
  :description "devops: DevOps Notes"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.clojure/tools.logging "1.1.0"]
                 [org.asciidoctor/asciidoctorj "2.5.1"]]

  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :resource-paths ["src/main/resources"]
  :target-path "target/%s"
  :jar-name "devops.jar"
  :uberjar-name "devops-uberjar.jar"
  :main pl.tomaszgigiel.devops.core
  :aot [pl.tomaszgigiel.devops.core]

  :profiles {:test {:resource-paths ["src/test/resources"]}
             :main-core {:main pl.tomaszgigiel.devops.core}
             :main-chapters-create {:main pl.tomaszgigiel.devops.chapters.create}
             :main-chapters-merge {:main pl.tomaszgigiel.devops.chapters.merge}
             :main-chapters-asciidoctor {:main pl.tomaszgigiel.devops.chapters.asciidoctor}
             :main-chapters-create-edn {:main pl.tomaszgigiel.devops.chapters.create-edn}}
  
  :aliases {"run-main-core" ["with-profile" "main-core" "run"]
            "run-main-chapters-create" ["with-profile" "main-chapters-create" "run"]
            "run-main-chapters-merge" ["with-profile" "main-chapters-merge" "run"]
            "run-main-chapters-asciidoctor" ["with-profile" "main-chapters-asciidoctor" "run"]
            "run-main-chapters-create-edn" ["with-profile" "main-chapters-create-edn" "run"]}
)
