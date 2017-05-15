#!/bin/bash

source ../../common-functions.sh

runTree com.greetings/src/main/java

echo ""
echo "${info} *** Displaying the contents (source files) of the 'com.greetings' module *** ${normal}"

echo ""
echo "${info} *** Compiling modules in 'com.greetings' *** ${normal}"


# *******************************************************************************************************************************
#
# The --module-path parameter of javac takes the name of directory where the compiled modules will be outputted.  Each module underneath should have its own directory
# The -d parameter (does not have a long-form) is the location of the module directory where the compiled module(s) will be placed.
#
#
#

javac --module-path com.greetings/target/classes \
                 -d com.greetings/target/classes/com.greetings \
      com.greetings/src/main/java/module-info.java \
      com.greetings/src/main/java/com/greetings/Main.java

# In the above case, compile Java classes (module-info.class & Main.class) are placed in the respective folders within
# 'com.greetings/target/classes/com.greetings' folder
#
# The javac command can take a chain of classes to compile to place in the respective module folder structure 'com.greetings/target/classes/com.greetings'.
#
# Run the 'javac --help' on the command-line to learn about more about the CLI arguments it can handle.
#
# *******************************************************************************************************************************


echo ""
echo "${info} *** Finished compiling modules into the 'com.greetings/target/classes' folder *** ${normal}"

echo ""
echo "${info} *** Displaying the contents (compiled modules) of the 'com.greetings/target/classes' folder *** ${normal}"
runTree com.greetings/target/classes

