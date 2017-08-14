#!/bin/bash

set -eu

source ../../common-functions.sh


echo ""
echo "${info} *** Compiling modules in 'org.astro' *** ${normal}"
javac -d org.astro/target/classes \
        org.astro/src/main/java/module-info.java \
        org.astro/src/main/java/org/astro/World.java

echo ""
echo "${info} *** Displaying the compiled modules in 'org.astro' *** ${normal}"
runTree org.astro/target/classes

echo ""
echo "${info} *** Compiling modules in 'com.greetings' *** ${normal}"
javac --module-path org.astro/target/classes \
                 -d com.greetings/target/classes \
      com.greetings/src/main/java/module-info.java \
      com.greetings/src/main/java/com/greetings/Main.java

echo ""
echo "${info} *** Displaying the compiled modules in 'com.greetings' *** ${normal}"
runTree com.greetings/target/classes

# See ../01_Greetings/compile.sh for explanations to above commands
