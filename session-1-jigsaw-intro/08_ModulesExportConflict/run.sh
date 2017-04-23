#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

echo 
echo "${info} *** Running 'com.greetings.Main' from within the mods folder. *** ${normal}"
echo "${info} *** Will fail because 'org.astro' and 'org.astro2' packages are exporting the same module 'org.astro'. *** ${normal}"
echo 
java --module-path mods \
     --module com.greetings/com.greetings.Main
