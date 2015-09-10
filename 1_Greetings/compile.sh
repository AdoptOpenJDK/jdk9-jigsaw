#!/bin/bash

set -eu

COM_GREETINGS_FOLDER="mods/com.greetings"

echo "Creating folder $COM_GREETINGS_FOLDER, if it does not exists."
mkdir -p $COM_GREETINGS_FOLDER

echo "Compiling modules in $COM_GREETINGS_FOLDER"
javac -modulepath mods -d $COM_GREETINGS_FOLDER \
        src/com.greetings/module-info.java src/com.greetings/com/greetings/Main.java

tree -fl $COM_GREETINGS_FOLDER