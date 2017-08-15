#!/bin/bash

set -eu

source ../../common-functions.sh

DESTINATION_FOLDER="mods"

echo ""
echo "${info} *** Displaying the contents (source files) of the 'src' folder *** ${normal}"
runTree src

echo ""
echo "${info} *** Compiling both modules into the '$DESTINATION_FOLDER' folder *** ${normal}"
javac -d $DESTINATION_FOLDER \
      [parameter to point to the folder with source files in a module] src $(find . -name "*.java")

#
# * look for tool references, quick started guides, and other documentation in the Java 9 Resource (https://github.com/AdoptOpenJDK/jdk9-jigsaw/blob/master/Java-9-Resources.md).
#

# *************************************************************************************
#
# ...
# In the above both the modules 'org.astro' and 'com.greetings' are compiled at the same time, dependency is fulfilled immediately.
#
# *************************************************************************************

echo ""
echo "${info} *** Displaying the contents (modules) of the 'mods' folder *** ${normal}"
runTree "$DESTINATION_FOLDER"

# See ../01_Greetings/compile.sh for explanations to above commands
