#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

COM_GREETINGS_FOLDER="mods/com.greetings"

echo ""
echo "${info} *** Creating folder $COM_GREETINGS_FOLDER, if it does not exists *** ${normal}"
mkdir -p $COM_GREETINGS_FOLDER

echo ""
echo "${info} *** Compiling modules in $COM_GREETINGS_FOLDER *** ${normal}"
javac --module-path mods -d $COM_GREETINGS_FOLDER \
        src/com.greetings/module-info.java src/com.greetings/com/greetings/Main.java

echo ""
echo "${info} *** Finished compiling modules into the $COM_GREETINGS_FOLDER folder *** ${normal}"

echo ""
echo "${info} *** Displaying the contents (modules) of the $COM_GREETINGS_FOLDER folder *** ${normal}"
tree -fl $COM_GREETINGS_FOLDER