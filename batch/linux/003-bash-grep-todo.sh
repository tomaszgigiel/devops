DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; grep -r "TODO" .; cd -) #
(cd $DIR_PROJECT; grep -r "TGL" .; cd -) #
