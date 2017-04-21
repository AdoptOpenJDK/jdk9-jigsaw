### --patch-module example

Developers that checkout `java.util.concurrent` classes from Doug Lea's CVS will be used to compiling the source files and deploying those classes with `-Xbootclasspath/p`.
`-Xbootclasspath/p` has been removed from Java, and its module replacement is the option `--patch-module` to override classes in a module. It can also be used to augment the contents of module. 
The `--patch-module` option is also supported by `javac` to compile code "as if" part of the module.

Perform the below commands to see the contents of the respective sources contained in the `src` folder:

- module `java.base` in `mypatches` folder
    
    ```
    $ tree mypatches
    ```
    
- module `java.base` in `src` folder

    ```
    $ cat src/java.base/java/util/concurrent/ConcurrentHashMap.java
    ```
     
**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

Both the modules are compiled together from the sources into the folder `mods` with the following commands:

    $ ./compile.sh
    
And we run the example with the following command:
    
    $ ./run.sh
    
Check the contents of both these script files (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.
