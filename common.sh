#!/bin/bash

set -eu

# Common variables
JDK_DESTINATION=$(echo ```pwd```)
JDK_FOLDER_NAME="jdk-9"
JDK_HOME_OS_SPECIFIC="$JDK_DESTINATION/$JDK_FOLDER_NAME"
JDK_DOWNLOAD_BASE_URL="http://download.java.net"

# Common functions
function checkIfJDKIsDownloaded() {
	echo ""
	echo "Checking if the JDK has already been downloaded..."
	if [ ! -f "$JDK_TAR_FILE_NAME" ]; then
    if [[ $JDK_NAME_GENERIC == "jdk-9+" ]]; then
  		echo "No JDK does not exist, downloading now..."
      wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" -O $JDK_TAR_FILE_NAME "${JDK_DOWNLOAD_BASE_URL}/java/jdk9/archive/${BUILD_NUMBER}/binaries/${JDK_TAR_FILE_NAME}"
    else
  		echo "No Jigsaw JDK does not exist, downloading now..."
	    wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" -O $JDK_TAR_FILE_NAME "${JDK_DOWNLOAD_BASE_URL}/java/jigsaw/archive/${BUILD_NUMBER}/binaries/${JDK_TAR_FILE_NAME}"
    fi
  else
    echo -e "The JDK ($JDK_TAR_FILE_NAME) has already been downloaded."
  fi
}

function checkIf_JAVA_HOME_IsSet() {
	JAVA_HOME_IS_SET=$(echo `echo $JAVA_HOME | grep "$JDK_HOME_OS_SPECIFIC"`)
	echo ""
	if [ -z $JAVA_HOME_IS_SET ]; then
		echo -e "JAVA_HOME does not point at $JDK_HOME_OS_SPECIFIC"
		echo -e "Please make it point at $JDK_HOME_OS_SPECIFIC with the below command:"
		echo ""
		echo -e "         export JAVA_HOME=$JDK_HOME_OS_SPECIFIC"
		echo ""
		echo "If needed add this to your .bashrc or .bash_profile settings."
	else
		echo -e "JAVA_HOME points at $JAVA_HOME"
	fi
}

function checkIf_PATH_IsSet() {
	PATH_IS_SET=$(echo `echo $PATH | grep "$JDK_HOME_OS_SPECIFIC_BIN"`)
	echo ""
	if [ -z $PATH_IS_SET ]; then
		echo -e "PATH does not contain $JDK_HOME_OS_SPECIFIC_BIN"
		echo -e "Please make it also point at it with the below command:"
		echo ""
		echo -e "         export PATH=$JDK_HOME_OS_SPECIFIC_BIN:\$PATH"
		echo ""
		echo "If needed add this to your .bashrc or .bash_profile settings."
	else
		echo -e "PATH contains $JDK_HOME_OS_SPECIFIC_BIN"
	fi
}

function checkIfTheRightJava9CommandsAreBeingExecuted() {
	echo ""
	echo "Running javac with the -version option, to verify if the right JDK 9 commands are being executed."
	javac -version
	
	echo ""
	echo "Running java with the -version option, to verify if the right JDK 9 commands are being executed."
	java -version	
}
