#!/bin/bash

set -eu

JDK_TAR_FILE_NAME="jigsaw-jdk-bin-linux-x64.tar.gz"
JDK_DESTINATION=$(echo ```pwd```)
JDK_FOLDER_NAME="jdk1.9.0"

function checkIfJigsawJDKIsDownloaded() {
	echo ""
	echo "Checking if the Jigsaw JDK has already been downloaded..."
	if [ ! -f "$JDK_TAR_FILE_NAME" ]; then
		echo "No Jigsaw JDK does not exist, downloading now..."
		wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" -O $JDK_TAR_FILE_NAME http://download.java.net/jigsaw/archive/b80/binaries/$JDK_TAR_FILE_NAME?q=download/jigsaw/archive/b80/binaries/$JDK_TAR_FILE_NAME 
	else
		echo -e "The Jigsaw JDK (\e[1;37m$JDK_TAR_FILE_NAME\e[0;37m) has already been downloaded."
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
		echo -e "The Jigsaw JDK (\e[1;37m$JDK_FOLDER_NAME\e[0;37m) has already been installed at \e[1;37m$JDK_DESTINATION.\e[0;37m"
	fi
}

function checkIf_JAVA_HOME_IsSet() {
	JAVA_HOME_IS_SET=$(echo `echo $JAVA_HOME | grep "$JDK_FOLDER_NAME"`)
	echo ""
	if [ -z $JAVA_HOME_IS_SET ]; then
		echo -e "JAVA_HOME does not point at \e[1;37m$JDK_DESTINATION/$JDK_FOLDER_NAME\e[0;37m"
		echo -e "Please make it points at \e[1;37m$JDK_DESTINATION/$JDK_FOLDER_NAME\e[0;37m with the below command:"
		echo ""
		echo -e "         \e[1;37mexport JAVA_HOME=$JDK_DESTINATION/$JDK_FOLDER_NAME\e[0;37m"
		echo ""
		echo "If needed add this to your .bashrc or .bash_profile settings."
	else
		echo -e "JAVA_HOME points at \e[1;37m$JAVA_HOME \e[0;37m"
	fi
}

function checkIf_PATH_IsSet() {
	PATH_IS_SET=$(echo `echo $PATH | grep "$JDK_FOLDER_NAME"`)
	echo ""
	if [ -z $PATH_IS_SET ]; then
		echo -e "PATH does not contain \e[1;37m$JDK_DESTINATION/$JDK_FOLDER_NAME\e[0;37m"
		echo -e "Please make it also point at it with the below command:"
		echo ""
		echo -e "         \e[1;37mexport PATH=$JDK_DESTINATION/$JDK_FOLDER_NAME:\$PATH\e[0;37m"
		echo ""
		echo "If needed add this to your .bashrc or .bash_profile settings."
	else
		echo -e "PATH contains  \e[1;37m$JDK_DESTINATION/$JDK_FOLDER_NAME\e[0;37m"
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