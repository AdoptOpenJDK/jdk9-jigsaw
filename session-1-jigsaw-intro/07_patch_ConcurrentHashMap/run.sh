#!/bin/bash

set -eu

source ../../common-functions.sh

echo 
echo "${info} *** Running without the patch from within the mods folder. *** ${normal}"
echo

java --module-path mods \
     --module com.greetings/com.greetings.Main

echo
echo "${info} *** Running with the patch from within the mods folder. *** ${normal}"
echo

java --module-path mods \
     [parameter patch the existing module with the newly compiled one*] \
     --module com.greetings/com.greetings.Main

# Hint: the clue is in one of the the parameters in javac command in the compile.sh (when you have resolved it)
# think whats the patch directory called?
