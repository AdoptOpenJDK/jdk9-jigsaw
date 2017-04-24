#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

echo 
echo "${info} *** Running from within the mods folder without arguments. *** ${normal}"
echo

java --module-path mods/main:lib \
     --module com.greetings/com.greetings.Main

echo
echo "${info} *** Running from within the mods folder with arguments. *** ${normal}"
echo

java --module-path mods/main:lib \
     --module com.greetings/com.greetings.Main \
    Alice Bob Charlie

echo
echo "${info} *** Running tests *** ${normal}"
echo

# Instead of running the compiled test code directly (which we could do), we're running it as a patch to the original code.
java --module-path mods/main:lib \
     --add-modules com.greetings \
     --patch-module com.greetings=mods/test/com.greetings \
     --module junit/org.junit.runner.JUnitCore \
    com.greetings.GreetTest
