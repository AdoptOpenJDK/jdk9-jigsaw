#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

runTree() 
{
  if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    cmd //c "tree /f /a $1"
  else
    tree -fl $1
  fi
}

DESTINATION_FOLDER="mods"

echo ""
echo "${info} *** Displaying the contents (source files) of the 'src' folder *** ${normal}"
runTree src

echo ""
echo "${info} *** Compiling both modules into the '$DESTINATION_FOLDER' folder *** ${normal}"
javac -d $DESTINATION_FOLDER \
      --module-source-path src $(find . -name "*.java")

# *************************************************************************************
#
# The --module-source-path parameter of javac takes input source files for multiple modules.
# In the above both the modules 'org.astro' and 'com.greetings' are compiled at the same time, dependency is fulfilled immediately.
#
# *************************************************************************************

echo ""
echo "${info} *** Displaying the contents (modules) of the 'mods' folder *** ${normal}"
runTree "$DESTINATION_FOLDER"

# See ../01_Greetings/compile.sh for explanations to above commands
