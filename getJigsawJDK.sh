#!/bin/bash

set -eu

JDK_DESTINATION=$(echo ```pwd```)

if [[ "$OSTYPE" == "darwin"* ]]; then
	JDK_TAR_FILE_NAME="jigsaw-jdk-bin-macosx-x86_64.tar.gz"
	JDK_FOLDER_NAME="jdk1.9.0.jdk"
else 
	JDK_TAR_FILE_NAME="jigsaw-jdk-bin-linux-x64.tar.gz"
	JDK_FOLDER_NAME="jdk1.9.0"
fi

function checkIfJigsawJDKIsDownloaded() {
	echo ""
	echo "Checking if the Jigsaw JDK has already been downloaded..."
	if [ ! -f "$JDK_TAR_FILE_NAME" ]; then
		echo "No Jigsaw JDK does not exist, downloading now..."
		wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" -O $JDK_TAR_FILE_NAME http://download.java.net/jigsaw/archive/b80/binaries/$JDK_TAR_FILE_NAME?q=download/jigsaw/archive/b80/binaries/$JDK_TAR_FILE_NAME 
	else
		echo -e "The Jigsaw JDK ($JDK_TAR_FILE_NAME) has already been downloaded."
	fi
}


function checkIfJigsawJDKIsInstalled() {
	echo ""
	echo "Checking if the Jigsaw JDK has already been installed..."
	if [ ! -d "$JDK_DESTINATION/$JDK_FOLDER_NAME" ]; then
		echo "Unpacking the Jigsaw JDK..."
		tar xzvf $JDK_TAR_FILE_NAME
		echo ""
		echo "Installing the Jigsaw JDK into $JDK_DESTINATION."
	else
		echo -e "The Jigsaw JDK ($JDK_FOLDER_NAME) has already been installed at $JDK_DESTINATION."
	fi
}

function checkIf_JAVA_HOME_IsSet() {
	JAVA_HOME_IS_SET=$(echo `echo $JAVA_HOME | grep "$JDK_FOLDER_NAME"`)
	echo ""
	if [ -z $JAVA_HOME_IS_SET ]; then
		echo -e "JAVA_HOME does not point at $JDK_DESTINATION/$JDK_FOLDER_NAME"
		echo -e "Please make it points at $JDK_DESTINATION/$JDK_FOLDER_NAME with the below command:"
		echo ""
		echo -e "         export JAVA_HOME=$JDK_DESTINATION/$JDK_FOLDER_NAME"
		echo ""
		echo "If needed add this to your .bashrc or .bash_profile settings."
	else
		echo -e "JAVA_HOME points at $JAVA_HOME "
	fi
}

function checkIf_PATH_IsSet() {
	PATH_IS_SET=$(echo `echo $PATH | grep "$JDK_FOLDER_NAME"`)
	echo ""
	if [ -z $PATH_IS_SET ]; then
		echo -e "PATH does not contain $JDK_DESTINATION/$JDK_FOLDER_NAME"
		echo -e "Please make it also point at it with the below command:"
		echo ""
		echo -e "         export PATH=$JDK_DESTINATION/$JDK_FOLDER_NAME:\$PATH"
		echo ""
		echo "If needed add this to your .bashrc or .bash_profile settings."
	else
		echo -e "PATH contains  $JDK_DESTINATION/$JDK_FOLDER_NAME"
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