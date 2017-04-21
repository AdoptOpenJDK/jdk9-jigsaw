#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

runTree() 
{
  if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    cmd //c "tree /f /a $1"
  else
    tree -fl $1
  fi
}

echo ""
echo "${info} *** Removing any existing executable directories *** ${normal}"
rm -rf executable

echo ""
echo "${info} *** Create an executable version of the com.greetings module *** ${normal}"
if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    # Windows users: please not that if the below fails due to JAVA_HOME substitution, please hard-code the JAVA_HOME path into the script
    jlink --module-path "${JAVA_HOME}\jmods;mlib" \
          --add-modules com.greetings \
          --output executable
else
    jlink --module-path "${JAVA_HOME}"/jmods:mlib \
          --add-modules com.greetings \
          --output executable
fi

echo ""
echo "${info} *** Displaying the contents (modules) of the 'executable' folder *** ${normal}"
runTree executable

# ****************************************************************************************************************************
# The value to `--module-path` is a PATH of directories containing the packaged modules.
# Replace the path separator ':' with ';' on Microsoft Windows.
# ****************************************************************************************************************************
