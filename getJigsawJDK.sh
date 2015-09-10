#!/bin/bash

set -eu

JDK_TAR_FILE_NAME="jigsaw-jdk-bin-linux-x64.tar.gz"
JDK_DESTINATION="/usr/lib/jvm"
JDK_FOLDER_NAME="jdk1.9.0"

echo ""
echo "Checking if the Jigsaw JDK has already been downloaded..."
if [ ! -f "$JDK_TAR_FILE_NAME" ]; then
	echo "No Jigsaw JDK does not exist, downloading now..."
	wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" -O $JDK_TAR_FILE_NAME http://download.java.net/jigsaw/archive/b80/binaries/$JDK_TAR_FILE_NAME?q=download/jigsaw/archive/b80/binaries/$JDK_TAR_FILE_NAME 
else
	echo -e "The Jigsaw JDK (\e[1;37m$JDK_TAR_FILE_NAME\e[0;37m) has already been downloaded."
fi

echo ""
echo "Checking if the Jigsaw JDK has already been installed..."
if [ ! -d "$JDK_DESTINATION/$JDK_FOLDER_NAME" ]; then
	echo "Unpacking the Jigsaw JDK..."
	tar xzvf $JDK_TAR_FILE_NAME
	echo "Installing the Jigsaw JDK into $JDK_DESTINATION"
	sudo mv -f $JDK_FOLDER_NAME $JDK_DESTINATION
else
	echo -e "The Jigsaw JDK has already been installed at \e[1;37m$JDK_DESTINATION/$JDK_FOLDER_NAME. \e[0;37m"
fi

JAVA_HOME_IS_SET=$(echo `echo $JAVA_HOME | grep "jdk1.9.0"`)
echo ""
if [ -z $JAVA_HOME_IS_SET ]; then
	echo -e "JAVA_HOME does not points at \e[1;37m$JDK_DESTINATION/$JDK_FOLDER_NAME\e[0;37m"
	echo -e "Please make it point to $JDK_DESTINATION/$JDK_FOLDER_NAME with the command \e[1;37mexport JAVA_HOME=$JDK_DESTINATION/$JDK_FOLDER_NAME\e[0;37m"
else
	echo -e "JAVA_HOME points at \e[1;37m$JAVA_HOME \e[0;37m"
fi
echo ""
echo "Running java and javac with -version options, to verify if JDK 1.9.0 has been installed successfully."
java -version
echo ""
javac -version