DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; sed -i 's/\[0m//g; s/\[0J//g; s/\[1000D//g; s/\[2K//g; s/\[2A//g; s/\[3A//g; s/\[//g; s///g' all-with-log.linux.log.txt; cd -) #
