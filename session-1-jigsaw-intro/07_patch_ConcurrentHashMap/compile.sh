#!/bin/bash

set -eu

source ../../common-functions.sh

PATCHES_FOLDER="mypatches/java.base"
MODS_FOLDER="mods/com.greetings"
SRC_FOLDER="src"

echo ""
echo "${info} *** Displaying the contents of the '$SRC_FOLDER' folder *** ${normal}"
runTree "$SRC_FOLDER"

echo ""
echo "${info} *** Compiling a new version of ConcurrentHashMap *** ${normal}"
javac --patch-module java.base=src \
      -d mypatches/java.base \
      src/java.base/java/util/concurrent/ConcurrentHashMap.java

echo ""
echo "${info} *** Compiling Main class*** ${normal}"

javac --module-path mods \
      -d mods/com.greetings/ \
      src/com.greetings/module-info.java \
      src/com.greetings/com/greetings/Main.java


echo ""
echo "${info} *** Displaying the contents in the '$PATCHES_FOLDER' folder *** ${normal}"
runTree "$PATCHES_FOLDER"

echo ""
echo "${info} *** Displaying the contents in the '$MODS_FOLDER' folder *** ${normal}"
runTree "$MODS_FOLDER"
