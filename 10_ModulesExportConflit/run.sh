#!/bin/bash

set -eu

echo 
echo "Running 'com.greetings.Main' from within the mods folder."
echo "Will fail because 'org.astro' and 'org.astro2' packages are exporting the same module 'org.astro'."
echo 
java -modulepath mods -m com.greetings/com.greetings.Main