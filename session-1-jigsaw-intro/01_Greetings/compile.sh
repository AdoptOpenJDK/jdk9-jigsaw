#!/bin/bash

set -eu

# Escape code
esc=$(echo -en "\033")

info="${esc}[0;33m"
normal=$(echo -en "${esc}[m\017")

COM_GREETINGS_FOLDER="mods/com.greetings"

runTree() 
{
  if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]] ; then
    cmd //c "tree /f /a $1"
  else
    tree -fl $1
  fi
}

runTree src

echo ""
echo "${info} *** Displaying the contents (source files) of the 'src' folder *** ${normal}"	

echo ""
echo "${info} *** Compiling modules in '$COM_GREETINGS_FOLDER' *** ${normal}"


# *******************************************************************************************************************************
#
# The --module-path parameter of javac takes the name of module folder (in this case 'mode') where the compiled modules will be outputted.
# The -d parameter (does not have a long-form) is the location of the module directory where the compiled module(s) will be placed.
#
# The compiler creates the 'mods' folder, if it does not exist already and places compiled modules into them.

javac --module-path mods \
      -d $COM_GREETINGS_FOLDER \
      src/com.greetings/module-info.java \
      src/com.greetings/com/greetings/Main.java

# In the above case, compile Java classes (module-info.class & Main.class) are placed in the respective folders within
# 'mod/com.greetings' folder
#
# The javac command can take a chain of classes to compile to place in the respective module folder structure 'mod/com.greetings'.
#
# Run the 'javac --help' on the command-line to learn about more about the CLI arguments it can handle.
#
# *******************************************************************************************************************************


echo ""
echo "${info} *** Finished compiling modules into the '$COM_GREETINGS_FOLDER' folder *** ${normal}"

echo ""
echo "${info} *** Displaying the contents (compiled modules) of the '$COM_GREETINGS_FOLDER' folder *** ${normal}"
runTree "$COM_GREETINGS_FOLDER"

