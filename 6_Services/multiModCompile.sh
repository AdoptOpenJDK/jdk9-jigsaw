#!/bin/bash

set -eu

DESTINATION_FOLDER="mods"
echo "Creating folder $DESTINATION_FOLDER, if it does not exists."
mkdir -p $DESTINATION_FOLDER

echo "Compiling modules in $DESTINATION_FOLDER"
javac -d $DESTINATION_FOLDER \
	-modulesourcepath src $(find src -name "*.java")

tree -fl $DESTINATION_FOLDER

#echo "Compiling modules in com.greetings - separately"
#javac -d mods/com.greetings/ -mp mods $(find src/com.greetings/ -name "*.java")

