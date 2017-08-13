#!/bin/bash

source ../../common-functions.sh

COM_GREETINGS_FOLDER="mods/com.greetings"

runTree src

echo ""
echo "${info} *** Displaying the contents (source files) of the 'src' folder *** ${normal}"	

echo ""
echo "${info} *** Compiling modules in '$COM_GREETINGS_FOLDER' *** ${normal}"


# *******************************************************************************************************************************
#
# ...
# The -d parameter (does not have a long-form) is the location of the module directory where the compiled module(s) will be placed.
#
# The compiler creates the 'mods' folder, if it does not exist already and places compiled modules into them.

javac [param to specify path to the module*] mods \
      -d [path to the compiled module**] \
      src/com.greetings/module-info.java \
      src/com.greetings/com/greetings/Main.java

#
# * can be either long or short form, look for documentation in the Java 9 Resource (https://github.com/AdoptOpenJDK/jdk9-jigsaw/blob/master/Java-9-Resources.md).
#
# ** can you environment variable containing the module path or literal path to the module
# *******************************************************************************************************************************

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

