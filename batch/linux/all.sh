DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
#
(cd $DIR_PROJECT; ./batch/linux/001-bash-info.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/002-bash-rm-target.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/003-bash-sed-replace-end-of-line.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/004-bash-grep-todo.sh; cd -) #
###(cd $DIR_PROJECT; ./batch/linux/005-bash-sed-replace-string.sh; cd -) #
###(cd $DIR_PROJECT; ./batch/linux/006-bash-sed-smooth-log-after.sh; cd -) #
#
(cd $DIR_PROJECT; ./batch/linux/301-lein-compile.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/302-lein-test-spec.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/303-lein-test.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/304-lein-run.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/305-lein-run-specific.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/306-lein-java-cp.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/307-lein-java-jar.sh; cd -) #
(cd $DIR_PROJECT; ./batch/linux/308-lein-uberjar.sh; cd -) #
###(cd $DIR_PROJECT; ./batch/linux/309-lein-repl.sh; cd -) #
#
(cd $DIR_PROJECT; ./batch/linux/007-bash-copy-to-index.sh; cd -) #