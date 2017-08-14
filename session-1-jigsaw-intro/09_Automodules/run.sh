#!/bin/bash

set -eu

source ../../common-functions.sh

echo 
echo "${info} *** Running from within the mods folder without arguments. *** ${normal}"
echo
if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    java --module-path "mods/main;lib" \
         --module com.greetings/com.greetings.Main
else
    java --module-path mods/main:lib \
         --module com.greetings/com.greetings.Main
fi

echo
echo "${info} *** Running from within the mods folder with arguments. *** ${normal}"
echo

if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    java --module-path "mods/main;lib" \
         --module com.greetings/com.greetings.Main \
        Alice Bob Charlie
else
    java --module-path mods/main:lib \
         --module com.greetings/com.greetings.Main \
        Alice Bob Charlie
fi

echo
echo "${info} *** Running tests *** ${normal}"
echo

# Instead of running the compiled test code directly (which we could do), we're running it as a patch to the original code.
if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    java --module-path "mods/main;lib" \
         --add-modules com.greetings \
         --patch-module com.greetings=mods/test/com.greetings \
         --module junit/org.junit.runner.JUnitCore \
        com.greetings.GreetTest
else
    java --module-path mods/main:lib \
         --add-modules com.greetings \
         --patch-module com.greetings=mods/test/com.greetings \
         --module junit/org.junit.runner.JUnitCore \
        com.greetings.GreetTest
fi