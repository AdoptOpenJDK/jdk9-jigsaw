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
echo "${info} *** Creating folder 'mlib' for the packages (jar files) to be created *** ${normal}"
mkdir -p mlib              # this must be created in order for the jar commands below to be successful

echo ""
echo "${info} *** Creating module org.astro as a package file (jar) *** ${normal}"
jar --create \
    --file mlib/org.astro@1.0.jar \
	--module-version 1.0 \
	-C mods/org.astro .

echo ""
echo "${info} *** Creating module com.greetings as a package file (jar) *** ${normal}"
jar --create \
    --file mlib/com.greetings.jar \
	--main-class=com.greetings.Main \
	-C mods/com.greetings .

echo ""
echo "${info} *** Displaying the contents (package files created) of the 'mlib' folder *** ${normal}"
runTree mlib

echo ""
echo "${info} *** Printing module description for org.astro as recorded in the module-info.class file in the package (jar) *** ${normal}"
jar --verbose \
    --describe-module \
    --file=mlib/org.astro@1.0.jar

    echo ""
echo "${info} *** Displaying contents of the module package org.astro@1.0 *** ${normal}"
jar --verbose \
    --list \
    --file=mlib/org.astro@1.0.jar

echo ""
echo "${info} *** Printing module description for com.greetings as recorded in the module-info.class file in the package (jar) *** ${normal}"
jar --verbose \
    --describe-module \
    --file=mlib/com.greetings.jar

    echo ""
echo "${info} *** Displaying contents of the module package com.greetings *** ${normal}"
jar --verbose \
    --list \
    --file=mlib/com.greetings.jar

# Run 'jar --help' and 'jar --help-extra' to learn about all the above parameters used to create package files (jar) and also read their descriptions
