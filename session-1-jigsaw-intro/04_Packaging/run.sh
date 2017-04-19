#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

echo ""
echo "${info} *** Running 'com.greetings.Main' from within the module package in the 'mlib' folder (running 'org.astro.World' from within the module package in the 'mlib' folder) *** ${normal}"
echo 
java --module-path mlib \
     --module com.greetings

# See ../01_Greetings/run.sh for explanations to above commands
# Also see how the --module-path can read module packages (jar files) in 'mlib' and take them as source modules for execution
