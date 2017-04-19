#!/bin/bash

set -eu


# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

echo ""
echo "${info} *** Running 'com.greetings.Main' from within the 'mods' folder (module 'com.greetings' is dependent on 'org.astro') *** ${normal}"
echo 
java --module-path mods \
     --module com.greetings/com.greetings.Main

# See ../01_Greetings/run.sh for explanations to above commands
