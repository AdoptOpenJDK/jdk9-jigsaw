#!/bin/bash

set -eu

# Import common variables
source common.sh

# Binary name and download path composed with these values and current version
JDK_NAME_GENERIC="jdk-9+"
JDK_NAME_LINUX="_linux-x64_bin.tar.gz"
JDK_NAME_OSX="_osx-x64_bin.dmg"

if [[ "$OSTYPE" == "darwin"* ]]; then
	JDK_DESTINATION="/Library/Java/JavaVirtualMachines"
fi

JDK_DOWNLOAD_HOME_PAGE="http://jdk.java.net/9/"
BUILD_NUMBER=$(curl $JDK_DOWNLOAD_HOME_PAGE/ | grep "Most recent build" | awk '{print $4}' |  tr -d "</h2>" | perl -nle 'print$& if m{jdk\-9\+\K\w+}')

if [[ "$OSTYPE" == "darwin"* ]]; then
  JDK_TAR_FILE_NAME=$JDK_NAME_GENERIC$BUILD_NUMBER$JDK_NAME_OSX
	JDK_FOLDER_NAME="$JDK_FOLDER_NAME.jdk"
	JDK_HOME_OS_SPECIFIC="$JDK_DESTINATION/$JDK_FOLDER_NAME/Contents/Home"
else
  JDK_TAR_FILE_NAME=$JDK_NAME_GENERIC$BUILD_NUMBER$JDK_NAME_LINUX
fi

JDK_HOME_OS_SPECIFIC_BIN="$JDK_HOME_OS_SPECIFIC/bin"

function checkIfJDKIsInstalled() {
	echo ""
	echo "Checking if the JDK has already been installed..."

	if [[ "$OSTYPE" == "darwin"* ]]; then
		echo "Since you are using Mac OS X/macOS, .tar.gz files are not provided anymore."
		echo "Please mount ${JDK_TAR_FILE_NAME} on Finder and install JDK 9. It will be installed at ${JDK_DESTINATION}/${JDK_FOLDER_NAME}."
		return
	fi

	if [ ! -d "$JDK_HOME_OS_SPECIFIC" ]; then
		echo "Unpacking the JDK..."
		tar xzvf $JDK_TAR_FILE_NAME
		echo ""
		echo "Installing the JDK into $JDK_HOME_OS_SPECIFIC."
	else
		echo -e "The JDK ($JDK_FOLDER_NAME) has already been installed at $JDK_DESTINATION."
	fi
}

checkIfJDKIsDownloaded
checkIfJDKIsInstalled
checkIf_JAVA_HOME_IsSet
checkIf_PATH_IsSet
checkIfTheRightJava9CommandsAreBeingExecuted
