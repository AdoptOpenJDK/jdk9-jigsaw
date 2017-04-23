#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

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

