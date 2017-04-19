#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

echo 
echo "${info} *** Running 'com.greetings.Main' inside the 'mods' folder. ${normal}"
echo

# *******************************************************************************************************************************
# The java command takes the -p parameter (long-form: --module-path) to refer to the 'mods' folder where the compiled modules are present.
# The -m parameter (long-form: --module) takes the name of the initial module to resolve, and the name of the main class to execute.
#

java --module-path mods \
     --module com.greetings/com.greetings.Main

# Run the 'java --help' on the command-line to learn about more about the CLI arguments it can handle.
# *******************************************************************************************************************************
