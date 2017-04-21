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
