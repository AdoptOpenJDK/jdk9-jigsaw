#!/bin/bash

set -eu

echo "Removing any existing executable directory"
rm -rf executable

echo
echo "Create an executable version of the com.greetings module"
jlink --module-path $JAVA_HOME/jmods:mlib --add-modules com.greetings --output executable

tree executable
