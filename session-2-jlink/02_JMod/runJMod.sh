#!/bin/bash

set -eu


# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

echo ""
echo "${info} *** Removing any existing greetings.jmod *** ${normal}"
rm -f greetings.jmod

echo
echo ""
echo "${info} *** Creating a module (greetings.jmod) from multiple modules / packages / classes with jlink. *** ${normal}"
jmod create \
     --class-path mods/com.greetings:mods/org.astro \
     greetings.jmod

echo ""
echo "${info} *** Enlisting the contents of the module (greetings.jmod) *** ${normal}"
jmod list greetings.jmod


echo "${info} *** Currently JMOD files can be used at compile time and link time, but not at run time. *** ${normal}"
echo "${info} *** Read further about JMod files at http://openjdk.java.net/jeps/261#Packaging:-JMOD-files *** ${normal}"
