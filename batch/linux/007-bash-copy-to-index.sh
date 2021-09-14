DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; cp src/test/resources/output/devops.html docs/index.html; cd -) #
