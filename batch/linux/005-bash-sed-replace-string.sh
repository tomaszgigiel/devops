DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; find ./src -type f -exec sed -i 's/newword/newword/g' {} \; ; cd -) #
