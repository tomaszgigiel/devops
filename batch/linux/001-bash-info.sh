DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; java -version; cd -) #
(cd $DIR_PROJECT; lein -version; cd -) #
