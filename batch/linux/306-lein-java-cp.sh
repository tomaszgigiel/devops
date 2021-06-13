DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; lein do clean, uberjar; java -cp target/uberjar/devops-uberjar.jar pl.tomaszgigiel.devops.core src/test/resources/devops.edn;cd -) #
