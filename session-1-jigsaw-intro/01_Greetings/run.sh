#!/bin/bash

set -eu

source ../../common.sh

echo 
echo "${info} *** Running 'com.greetings.Main' inside the 'mods' folder. ${normal}"
echo

# *******************************************************************************************************************************
# The -p parameter (long-form: --module-path) of the java command takes one or more paths to module folders where the compiled modules are present (in the above case its 'mods').
# The -m parameter (long-form: --module) takes the name of the initial module to resolve, and the name of the main class to execute.
#

java --module-path mods \
     --module com.greetings/com.greetings.Main

# Run the 'java --help' on the command-line to learn about more about the CLI arguments it can handle.
# *******************************************************************************************************************************
echo
