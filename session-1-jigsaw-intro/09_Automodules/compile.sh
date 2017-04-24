#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

runTree() 
{
  if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    cmd //c "tree /f /a $1"
  else
    tree -fl $1
  fi
}

MODS="mods"
COM_GREETINGS_MAIN_FOLDER="${MODS}/main/com.greetings"
COM_GREETINGS_TEST_FOLDER="${MODS}/test/com.greetings"

echo ""
echo "${info} *** Cleaning up old compiled classes *** ${normal}"
rm -rf ${MODS}

echo ""
echo "${info} *** Compiling main module in $COM_GREETINGS_MAIN_FOLDER *** ${normal}"
javac --module-path lib \
      -d ${COM_GREETINGS_MAIN_FOLDER} \
      src/com.greetings/module-info.java \
      src/com.greetings/main/java/com/greetings/Greet.java \
      src/com.greetings/main/java/com/greetings/Main.java

echo ""
echo "${info} *** Compiling test module in $COM_GREETINGS_TEST_FOLDER *** ${normal}"
# We have to compile the test code (including the tested code) again to have that separate from the production code.
javac --module-path mods:lib \
      -d ${COM_GREETINGS_TEST_FOLDER} \
      src/com.greetings/module-info.java \
      src/com.greetings/main/java/com/greetings/Greet.java \
      src/com.greetings/test/java/com/greetings/GreetTest.java

echo ""
echo "${info} *** Displaying the contents (modules) of the '$MODS' folder *** ${normal}"
runTree "$MODS"
