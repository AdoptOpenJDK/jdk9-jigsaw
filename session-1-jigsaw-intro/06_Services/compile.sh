#!/bin/bash

set -eu

source ../../common-functions.sh

DESTINATION_FOLDER="mods"

echo ""
echo "${info} *** Compiling modules in $DESTINATION_FOLDER *** ${normal}"
javac -d $DESTINATION_FOLDER \
      --module-source-path src \
      $(find src -name "*.java")

echo ""
echo "${info} *** Compiling modules in com.greetings - separately *** ${normal}"
javac -d mods/com.greetings/ \
      --module-path mods \
      $(find src/com.greetings/ -name "*.java")

echo ""
echo "${info} *** Displaying the contents (modules) of the '$DESTINATION_FOLDER' folder *** ${normal}"
runTree "$DESTINATION_FOLDER"
