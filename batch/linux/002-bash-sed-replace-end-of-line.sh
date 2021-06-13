DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; find ./batch/linux/ -type f -exec sed -i 's/\r$//g' {} \; ; cd -) #
