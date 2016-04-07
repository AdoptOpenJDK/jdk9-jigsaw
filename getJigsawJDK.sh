#!/bin/bash

set -eu

JDK_DESTINATION=$(echo ```pwd```)
JDK_FOLDER_NAME="jdk-9"
JDK_HOME_OS_SPECIFIC="$JDK_DESTINATION/$JDK_FOLDER_NAME"

if [[ "$OSTYPE" == "darwin"* ]]; then
	JDK_TAR_FILE_NAME="jigsaw-jdk-9-ea+111_osx-x64_bin.tar.gz"
	JDK_FOLDER_NAME="$JDK_FOLDER_NAME.jdk"
	JDK_HOME_OS_SPECIFIC="$JDK_DESTINATION/$JDK_FOLDER_NAME/Contents/Home"
else 
	JDK_TAR_FILE_NAME="jigsaw-jdk-9-ea+111_linux-x64_bin.tar.gz"
fi

JDK_HOME_OS_SPECIFIC_BIN="$JDK_HOME_OS_SPECIFIC/bin"

function checkIfJigsawJDKIsDownloaded() {
	echo ""
	echo "Checking if the Jigsaw JDK has already been downloaded..."
	if [ ! -f "$JDK_TAR_FILE_NAME" ]; then
		echo "No Jigsaw JDK does not exist, downloading now..."
		wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" -O $JDK_TAR_FILE_NAME http://www.java.net/download/java/jigsaw/archive/111/binaries/$JDK_TAR_FILE_NAME 
	else
		echo -e "The Jigsaw JDK ($JDK_TAR_FILE_NAME) has already been downloaded."
	fi
}


function checkIfJigsawJDKIsInstalled() {
	echo ""
	echo "Checking if the Jigsaw JDK has already been installed..."
	if [ ! -d "$JDK_HOME_OS_SPECIFIC" ]; then
		echo "Unpacking the Jigsaw JDK..."
		tar xzvf $JDK_TAR_FILE_NAME
		echo ""
		echo "Installing the Jigsaw JDK into $JDK_HOME_OS_SPECIFIC."
	else
		echo -e "The Jigsaw JDK ($JDK_FOLDER_NAME) has already been installed at $JDK_DESTINATION."
	fi
}

function checkIf_JAVA_HOME_IsSet() {
	JAVA_HOME_IS_SET=$(echo `echo $JAVA_HOME | grep "$JDK_HOME_OS_SPECIFIC"`)
	echo ""
	if [ -z $JAVA_HOME_IS_SET ]; then
		echo -e "JAVA_HOME does not point at $JDK_HOME_OS_SPECIFIC"
		echo -e "Please make it points at $JDK_HOME_OS_SPECIFIC with the below command:"
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

checkIfJigsawJDKIsDownloaded
checkIfJigsawJDKIsInstalled
checkIf_JAVA_HOME_IsSet
checkIf_PATH_IsSet
checkIfTheRightJava9CommandsAreBeingExecuted