#!/bin/bash

set -eu

source ../../common-functions.sh

COM_GREETINGS_FOLDER="com.greetings/target/classes/com.greetings"
ORG_ASTRO_FOLDER="org.astro/target/classes/org.astro"

echo ""
echo "${info} *** Displaying the contents (source files) of the 'org.astro' folder *** ${normal}"
runTree org.astro/src/main/java

echo ""
echo "${info} *** Displaying the contents (source files) of the 'com.greetings' folder *** ${normal}"
runTree com.greetings/src/main/java

echo ""
echo "${info} *** Compiling module in folder $ORG_ASTRO_FOLDER *** ${normal}"
javac -d org.astro/target/classes/org.astro \
      org.astro/src/main/java/module-info.java \
      org.astro/src/main/java/org/astro/World.java

echo ""
echo "${info} *** Displaying the contents (modules) of the org.astro/target/classes folder *** ${normal}"
runTree org.astro/target/classes

echo ""
echo "${info} *** Compiling module in folder '$COM_GREETINGS_FOLDER' and linking it with module '$ORG_ASTRO_FOLDER' *** ${normal}"
javac --module-path org.astro/target/classes \
                 -d com.greetings/target/classes/com.greetings \
      com.greetings/src/main/java/module-info.java \
      com.greetings/src/main/java/com/greetings/Main.java

# *************************************************************************************
#
# In the above the module 'org.astro' exports the package 'org.astro' and the module 'com.greetings' imports the package 'org.astro'.
# The modules must be compiled in this order, in order for the compiler to find the dependency and fulfill it.
#
# *************************************************************************************

echo ""
echo "${info} *** Displaying the contents (modules) of the '$COM_GREETINGS_FOLDER' folder *** ${normal}"
runTree "$COM_GREETINGS_FOLDER"

# See ../01_Greetings/compile.sh for explanations to above commands
