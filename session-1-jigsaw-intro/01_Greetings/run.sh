#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

echo 
echo "${info} *** Running 'com.greetings.Main' inside the 'mods' folder. ${normal}"
echo 
java --module-path mods -m com.greetings/com.greetings.Main
