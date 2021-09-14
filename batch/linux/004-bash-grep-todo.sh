DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; grep -r "TODO" . --exclude={all-with-log.linux.log.txt,all-with-log.msdos.log.txt,empty.html,devops.html,index.html}; cd -) #
(cd $DIR_PROJECT; grep -r "TGL" . --exclude={all-with-log.linux.log.txt,all-with-log.msdos.log.txt,empty.html,devops.html,index.html}; cd -) #
