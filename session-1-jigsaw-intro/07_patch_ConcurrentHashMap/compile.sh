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
javac [param to patch a module*] \
      -d [folder**] \
      [new patch class file***]


#
# * long param, name of the JDK module and folder name containing the sources
# ** destination folder where the compiled module will be placed
# *** filename (with path) to the new class file to patch with (this file will replace the existing class file in the module defined above)
#
# look for documentation in the Java 9 Resource (https://github.com/AdoptOpenJDK/jdk9-jigsaw/blob/master/Java-9-Resources.md)
#

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
