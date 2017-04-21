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


echo ""
echo "${info} *** Creating the mlib folder for the jars *** ${normal}"
mkdir -p mlib

echo ""
echo "${info} *** Creating module org.astro as a jar in the 'mlib' folder *** ${normal}"
jar --create \
    --file mlib/org.astro@1.0.jar \
	--module-version 1.0  \
	-C mods/org.astro .

echo ""
echo "${info} *** Creating module com.greetings as a jar in the 'mlib' folder *** ${normal}"
jar --create \
    --file mlib/com.greetings.jar \
	--main-class=com.greetings.Main \
	-C mods/com.greetings .


echo ""
echo "${info} *** Displaying the contents (jars) of the 'mlib' folder *** ${normal}"
runTree mlib

echo ""
echo "${info} *** Printing module description for org.astro jar file *** ${normal}"
jar --describe-module  \
    --file=mlib/org.astro@1.0.jar

echo ""
echo "${info} *** Printing module description for com.greetings jar file *** ${normal}"
jar --describe-module  \
    --file=mlib/com.greetings.jar
