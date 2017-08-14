#!/bin/bash

set -eu

source ../../common-functions.sh

echo ""
echo "${info} *** Size (footprint) of the distributable JDK binary (executable folder) *** ${normal}"
du -h executable

echo ""
echo "${info} *** Module 'com.greetings' and its transient dependencies are contained in the 'executable' folder itself *** ${normal}"
echo "${info} *** Look for the file by the name 'modules' *** ${normal}"
ls executable/lib

echo
echo "${info} *** The 'modules' file in the 'executable/lib' folder contains the compressed image of all the modules (JDK + your application modules) *** ${normal}"
echo "${info} *** We know this cause we can run 'jxxxxx' on 'modules' and see its contents, below is some info on the image file *** ${normal}"
[java command to do with image format] [just want basic info] executable/lib/modules


echo
echo "${info} *** A summary of all the modules it contains *** ${normal}"
[java command to do with image format] [want to enlist modules in the folder] executable/lib/modules | grep "Module:"

echo
echo "${info} *** Run the command 'jxxxx list executable/lib/modules | less' at the command line to see the detailed contents of the 'executable/lib/modules' image ${normal}"
echo "${info} *** 'jxxxxx --help' prints more help on this tool ${normal}"


