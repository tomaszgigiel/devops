(defproject devops "1000"
  :description "devops: DevOps Notes"
  :url "http://tomaszgigiel.pl"
  :license {:name "Apache License"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.clojure/tools.logging "1.1.0"]]

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
             :main-pre {:main pl.tomaszgigiel.devops.pre}}
  
  :aliases {"run-main-core" ["with-profile" "main-core" "run"]
            "run-main-pre" ["with-profile" "main-pre" "run"]}
)
