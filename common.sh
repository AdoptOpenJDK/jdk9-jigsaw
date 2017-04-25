#!/bin/bash

# This is just common bash code used by scripts in the modules

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

java -version 2>&1 | grep -q '"9' || echo "**** Fix your PATH! ****" && java -version && exit 1

