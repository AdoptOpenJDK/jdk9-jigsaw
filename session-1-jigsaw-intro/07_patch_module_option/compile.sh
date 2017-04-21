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

PATCHES_FOLDER="mypatches/java.base"
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
echo "${info} *** Displaying the contents in the '$PATCHES_FOLDER' folder *** ${normal}"
runTree "$PATCHES_FOLDER"
