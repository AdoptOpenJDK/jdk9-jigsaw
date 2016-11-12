#!/bin/bash

set -eu

echo 
echo "Running 'com.greetings.Main' from within the mods folder."
echo 
java --module-path mods -m com.greetings/com.greetings.Main
