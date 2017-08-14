#!/bin/bash

set -eu

source ../../common-functions.sh

echo 
echo "${info} *** Running 'com.greetings.Main' from within the mods folder. *** ${normal}"
echo "${info} *** Will fail because 'org.astro' and 'org.astro2' packages are exporting the same module 'org.astro'. *** ${normal}"
echo
echo "${info} *** Are you able to resolve this conflict ?*** ${normal}"
echo "${info} *** If so please create a PR against the exercise-solutions branch of this repo *** ${normal}"
echo

java --module-path mods \
     --module com.greetings/com.greetings.Main

