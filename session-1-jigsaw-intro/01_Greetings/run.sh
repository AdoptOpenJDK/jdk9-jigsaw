#!/bin/bash

set -eu

source ../../common-functions.sh

echo 
echo "${info} *** Running 'com.greetings.Main' inside the 'mods' folder. ${normal}"
echo

# *******************************************************************************************************************************
# The -p parameter (long-form: --module-path) of the java command takes one or more paths to module folders where the compiled modules are present (in the above case its 'mods').
# ...
#

java --module-path mods \
     [reference to the compiled class*]

#
# * parameter can be either long or short form, with module name and fully qualified class name
# look for tool references, quick started guides, and other documentation in the Java 9 Resource (https://github.com/AdoptOpenJDK/jdk9-jigsaw/blob/master/Java-9-Resources.md).
#
# *************************************************************************************
#
# Run the 'java --help' on the command-line to learn about more about the CLI arguments it can handle.
# *******************************************************************************************************************************
echo
