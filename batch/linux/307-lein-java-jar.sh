DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; lein do clean, uberjar; java -jar target/uberjar/devops-uberjar.jar src/test/resources/devops.edn;cd -) #
