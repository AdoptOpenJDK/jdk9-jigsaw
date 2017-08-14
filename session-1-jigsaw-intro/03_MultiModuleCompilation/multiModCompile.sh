#!/bin/bash

set -eu

source ../../common-functions.sh


echo ""
echo "${info} *** Displaying the contents (source files) of the 'org.astro' folder *** ${normal}"
runTree org.astro/src/main/java

echo ""
echo "${info} *** Displaying the contents (source files) of the 'com.greetings' folder *** ${normal}"
runTree com.greetings/src/main/java

echo ""
echo "${info} *** Compiling both modules into the 'mods' folder *** ${normal}"


if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    SEP=";"
else
    SEP=":"
fi

javac -d mods \
      --module-source-path "org.astro/src/main/java${SEP}com.greetings/src/main/java" \
      $(find . -name *.java)

      

# *************************************************************************************
#
# The --module-source-path parameter of javac takes input source files for multiple modules.
# In the above both the modules 'org.astro' and 'com.greetings' are compiled at the same time, dependency is fulfilled immediately.
#
# *************************************************************************************

echo ""
echo "${info} *** Displaying the contents (modules) of the 'mods' folder *** ${normal}"
runTree mods

# See ../01_Greetings/compile.sh for explanations to above commands
