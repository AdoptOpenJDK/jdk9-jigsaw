#!/bin/bash

set -eu

echo 
echo "Running 'com.greetings.Main' from within the mods folder."
echo "Depends on 'com.socket' & 'com.fastsocket'from within the mods folder."
echo
java --module-path mods --module com.greetings/com.greetings.Main
