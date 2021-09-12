DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; rm -rf target; cd -) #
(cd $DIR_PROJECT; rm -rf src/test/resources/output/{*,.*}; cd -) #
