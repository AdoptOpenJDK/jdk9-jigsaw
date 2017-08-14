#!/bin/bash

set -eu

source ../../common-functions.sh

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
if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    javac --module-path "mods;lib" \
          -d ${COM_GREETINGS_TEST_FOLDER} \
          src/com.greetings/module-info.java \
          src/com.greetings/main/java/com/greetings/Greet.java \
          src/com.greetings/test/java/com/greetings/GreetTest.java
else
    javac --module-path mods:lib \
          -d ${COM_GREETINGS_TEST_FOLDER} \
          src/com.greetings/module-info.java \
          src/com.greetings/main/java/com/greetings/Greet.java \
          src/com.greetings/test/java/com/greetings/GreetTest.java
fi

echo ""
echo "${info} *** Displaying the contents (modules) of the '$MODS' folder *** ${normal}"
runTree "$MODS"
