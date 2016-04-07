#!/bin/bash

set -eu

echo "Removing any existing executable directory"
rm -rf executable

echo
echo "Create an executable version of the com.greetings module"
jlink --modulepath $JAVA_HOME/jmods:mlib --addmods com.greetings --output executable

tree executable
