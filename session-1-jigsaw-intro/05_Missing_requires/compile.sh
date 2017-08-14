#!/bin/bash

set -eu

source ../../common-functions.sh

COM_GREETINGS_FOLDER="com.greetings/target/classes"
ORG_ASTRO_FOLDER="org.astro/target/classes"

echo ""
echo "${info} *** Compiling modules in $ORG_ASTRO_FOLDER (with 'exports' commented out) *** ${normal}"
javac -d $ORG_ASTRO_FOLDER \
       org.astro/src/main/java/module-info.java \
       org.astro/src/main/java/org/astro/World.java

echo ""
echo "${info} *** Displaying the contents (modules) of the '$ORG_ASTRO_FOLDER' folder *** ${normal}"
runTree "$ORG_ASTRO_FOLDER"

echo ""
echo "${info} *** Compiling modules in $COM_GREETINGS_FOLDER (fails with an error due to module 'org.astro' not being visible) *** ${normal}"
javac --module-path $ORG_ASTRO_FOLDER \
      -d $COM_GREETINGS_FOLDER \
      com.greetings/src/main/java/module-info.java \
      com.greetings/src/main/java/com/greetings/Main.java

# ********************************************************************************
# Compilation does not finish due to compilation error, no artifacts are created.
# Execution does not proceed further, script exists at this point.
# ********************************************************************************

echo ""
echo "${info} *** Displaying the contents (modules) of the '$COM_GREETINGS_FOLDER' folder *** ${normal}"
runTree "$COM_GREETINGS_FOLDER"

# See ../01_Greetings/compile.sh for explanations to above commands
