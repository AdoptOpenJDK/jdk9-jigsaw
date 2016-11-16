#!/bin/bash

set -eu

echo 
echo "Running 'com.greetings.Main' from within the module (jar) in the mlib folder."
echo "Dependent on 'org.astro.World' from within the module (jar) in the mlib folder."
echo 
java --module-path mlib --module com.greetings
