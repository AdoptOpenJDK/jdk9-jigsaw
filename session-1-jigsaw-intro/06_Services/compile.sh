#!/bin/bash

set -eu

source ../../common-functions.sh

DESTINATION_FOLDER="mods"

echo ""
echo "${info} *** Compiling modules in $DESTINATION_FOLDER *** ${normal}"
javac --module-source-path src \
      -d $DESTINATION_FOLDER \
      $(find src -name "*.java")

echo ""
echo "${info} *** Compiling modules in com.greetings - separately *** ${normal}"

javac --module-path mods \
      -d mods/com.greetings/ \
      $(find src/com.greetings/ -name "*.java")

echo ""
echo "${info} *** Displaying the contents (modules) of the '$DESTINATION_FOLDER' folder *** ${normal}"
runTree "$DESTINATION_FOLDER"
