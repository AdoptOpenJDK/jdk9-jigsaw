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

COM_GREETINGS_FOLDER="mods/com.greetings"
ORG_ASTRO_FOLDER="mods/org.astro"
ORG_ASTRO2_FOLDER="mods/org.astro2"

echo ""
echo "${info} *** Compiling modules in $ORG_ASTRO_FOLDER *** ${normal}"
javac -d $ORG_ASTRO_FOLDER \
        src/org.astro/module-info.java \
        src/org.astro/org/astro/World.java

echo ""
echo "${info} *** Displaying the contents (modules) of the '$ORG_ASTRO_FOLDER' folder *** ${normal}"
runTree "$ORG_ASTRO_FOLDER"

echo ""
echo "${info} *** Compiling modules in $ORG_ASTRO2_FOLDER *** ${normal}"
javac -d $ORG_ASTRO2_FOLDER \
      src/org.astro2/module-info.java \
      src/org.astro2/org/astro/World.java

echo ""
echo "${info} *** Displaying the contents (modules) of the '$ORG_ASTRO2_FOLDER' folder *** ${normal}"
runTree "$ORG_ASTRO2_FOLDER"

echo ""
echo "${info} *** Compiling modules in $COM_GREETINGS_FOLDER *** ${normal}"
javac --module-path mods \
      -d $COM_GREETINGS_FOLDER \
      src/com.greetings/module-info.java \
      src/com.greetings/com/greetings/Main.java

echo ""
echo "${info} *** Displaying the contents (modules) of the '$COM_GREETINGS_FOLDER' folder *** ${normal}"
runTree "$COM_GREETINGS_FOLDER"
