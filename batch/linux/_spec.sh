DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
#(cd $DIR_PROJECT; lein do clean, run-main-create-faq-chapters src/test/resources/devops.edn; cd -) #
#(cd $DIR_PROJECT; lein do clean, run-main-create-faq-chapters-merged src/test/resources/devops.edn; cd -) #
#(cd $DIR_PROJECT; lein do clean, run-main-create-faq-chapters-asciidoctor src/test/resources/devops.edn; cd -) #
#(cd $DIR_PROJECT; lein do clean, run-main-create-faq-chapters-edn src/test/resources/devops.edn; cd -) #
#(cd $DIR_PROJECT; lein do clean, run-main-create-faq-chapters-freemarker src/test/resources/devops.edn; cd -) #
(cd $DIR_PROJECT; lein do clean, run-main-create-quotes-edn src/test/resources/devops.edn; cd -) #
(cd $DIR_PROJECT; lein do clean, run-main-create-quotes-freemarker src/test/resources/devops.edn; cd -) #
