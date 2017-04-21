#!/bin/bash

set -eu

runTree() 
{
  if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    cmd //c "tree /f /a $1"
  else
    tree -fl $1
  fi
}

echo "Removing any existing executable directory"
rm -rf executable

echo
echo "Create an executable version of the com.greetings module"
jlink --module-path $JAVA_HOME/jmods:mlib --add-modules com.greetings --output executable

runTree executable
