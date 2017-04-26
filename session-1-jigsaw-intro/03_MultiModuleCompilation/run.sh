#!/bin/bash

set -eu

source ../../common-functions.sh

echo ""
echo "${info} *** Running 'com.greetings.Main' from within the mods folder (module 'com.greetings' is dependent on 'org.astro'). *** ${normal}"
echo 
java --module-path mods \
     --module com.greetings/com.greetings.Main

# See ../01_Greetings/run.sh for explanations to above commands
