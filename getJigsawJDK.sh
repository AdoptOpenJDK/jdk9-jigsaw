#!/bin/bash

set -eu

# Import common variables
source common.sh

# Binary name and download path composed with these values and current version
JDK_NAME_GENERIC="jigsaw-jdk-9-ea+"
JDK_NAME_LINUX="_linux-x64_bin.tar.gz"
JDK_NAME_OSX="_osx-x64_bin.tar.gz"

JDK_DOWNLOAD_HOME_PAGE="http://jdk.java.net/jigsaw/"
BUILD_NUMBER=$(curl $JDK_DOWNLOAD_HOME_PAGE/ | grep "Most recent build" | awk '{print $4}' |  tr -d "</h2>" | perl -nle 'print$& if m{jdk\-9\+\K\w+}')

if [[ "$OSTYPE" == "darwin"* ]]; then
  JDK_TAR_FILE_NAME=$JDK_NAME_GENERIC$BUILD_NUMBER$JDK_NAME_OSX
	JDK_FOLDER_NAME="$JDK_FOLDER_NAME.jdk"
	JDK_HOME_OS_SPECIFIC="$JDK_DESTINATION/$JDK_FOLDER_NAME/Contents/Home"
else
  JDK_TAR_FILE_NAME=$JDK_NAME_GENERIC$BUILD_NUMBER$JDK_NAME_LINUX
fi

JDK_HOME_OS_SPECIFIC_BIN="$JDK_HOME_OS_SPECIFIC/bin"


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

checkIfJDKIsDownloaded
checkIfJigsawJDKIsInstalled
checkIf_JAVA_HOME_IsSet
checkIf_PATH_IsSet
checkIfTheRightJava9CommandsAreBeingExecuted
