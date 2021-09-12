DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; script -c "./batch/linux/all.sh" all-with-log.linux.log.txt; cd -) #
(cd $DIR_PROJECT; ./batch/linux/006-bash-sed-smooth-log-after.sh; cd -) #
