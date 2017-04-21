#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

echo 
echo "${info} *** Running  from within the mods folder. *** ${normal}"
echo

java --patch-module java.base=mypatches/java.base java.util.concurrent.ConcurrentHashMap
