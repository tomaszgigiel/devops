DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; lein do clean, run-main-chapters-create src/test/resources/devops.edn; cd -) #
(cd $DIR_PROJECT; lein do clean, run-main-chapters-merge src/test/resources/devops.edn; cd -) #
(cd $DIR_PROJECT; lein do clean, run-main-chapters-asciidoctor src/test/resources/devops.edn; cd -) #
(cd $DIR_PROJECT; lein do clean, run-main-chapters-create-edn src/test/resources/devops.edn; cd -) #
