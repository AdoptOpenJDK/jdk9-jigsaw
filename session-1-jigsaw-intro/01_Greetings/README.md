### Greetings example

This first example is a module named `com.greetings` that simply prints "Greetings!". 

The module consists of two source files: 
the module declaration (`module-info.java`) and the `Main` class.

Java naming conventions for modules and packages: the `src` folder contains a module `com.greetings` (file-system: `com.greetings`) which in this case is also the name of the module.
This module contains a package called `com.greetings` (file-system: `com/greetings`).

Perform the below commands to see the contents of the respective Java classes contained in the `src` folder:

    $ cat src/com.greetings/module-info.java

    $ cat src/com.greetings/com/greetings/Main.java

**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

The source code is compiled into the directory `mods/com.greetings` with the following commands:

    $ ./compile.sh
    
And we run the example with the following command:
    
    $ ./run.sh
    
Check the contents of both these script files (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

##### Minor confusion alert!

Please do not get confused with the naming conventions of modules with that of packages, here's how they go:

Modules in Java follow the the below convention, the name is the literal name of the folder itself:

| Module name         | File system naming*   |
|---------------------|-----------------------|
| com.greetings.hello |  com.greetings.hello  |
| com.greetings       |  com.greetings        |
| com                 |  com                  |


Packages in Java follow the usual convention:

| Package name        | File system naming*   |
|---------------------|-----------------------|
| com.greetings.hello | com/greetings/hello   |
| com.greetings       | com/greetings         |
| abc                 | abc                   |

*by `File system naming`, we mean the `directory structure` created as a result of a module or package
