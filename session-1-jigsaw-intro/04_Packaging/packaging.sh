#!/bin/bash

set -eu

source ../../common-functions.sh

echo ""
echo "${info} *** Creating folder 'mlib' for the packages (jar files) to be created *** ${normal}"
mkdir -p mlib              # this must be created in order for the jar commands below to be successful

echo ""
echo "${info} *** Creating module org.astro as a package file (jar) *** ${normal}"
jar --create \
    [parameter to specify file, in the mlib folder, specify module name, with @1.0 and .jar extension*] \
	[parameter to specify module, set it to version 1.0] \
	-C mods/org.astro .

echo ""
echo "${info} *** Creating module com.greetings as a package file (jar) *** ${normal}"
jar --create \
    --file mlib/com.greetings.jar \
	[parameter to specify the main class, and name the fully qualified main class] \
	-C mods/com.greetings .

echo ""
echo "${info} *** Displaying the contents (package files created) of the 'mlib' folder *** ${normal}"
runTree mlib

echo ""
echo "${info} *** Printing module description for org.astro as recorded in the module-info.class file in the package (jar) *** ${normal}"
jar [verbose mode] \
    [module description param] \
    --file=mlib/org.astro@1.0.jar

    echo ""
echo "${info} *** Displaying contents of the module package org.astro@1.0 *** ${normal}"
jar [verbose mode] \
    [list contents of jar param] \
    --file=mlib/org.astro@1.0.jar

echo ""
echo "${info} *** Printing module description for com.greetings as recorded in the module-info.class file in the package (jar) *** ${normal}"
jar [verbose mode] \
    [module description param] \
    --file=mlib/com.greetings.jar

    echo ""
echo "${info} *** Displaying contents of the module package com.greetings *** ${normal}"
jar [verbose mode] \
    [list contents of jar param] \
    --file=mlib/com.greetings.jar

# Run 'jar --help' and 'jar --help-extra' to learn about all the above parameters used to create package files (jar) and also read their descriptions
