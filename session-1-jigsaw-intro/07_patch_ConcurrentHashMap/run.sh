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
     --patch-module java.base=mypatches/java.base \
     --module com.greetings/com.greetings.Main

