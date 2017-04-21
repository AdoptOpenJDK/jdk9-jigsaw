### Packaging example

In the examples so far then the contents of the compiled modules were exploded on the file system. For transportation and deployment purposes then it is usually more convenient to package a module as a modular JAR. 

A modular JAR is a regular JAR file that has a `module-info.class` in its top-level directory, it creates `org.astro@1.0.jar` and `com.greetings.jar` in directory `mlib`.

**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

Perform the below commands to see the contents of the respective sources contained in the `src` folder:
    
    $ tree -fl src

    or 

    $ cmd //c "tree /f /a src"    (for Windows users)

The modules are compiled from the sources into the folder `mods` with the following commands:

    $ ./compile.sh

Now create the packages (jar files) with the below command:

    $ ./packing.sh

Perform the below command to see the packages (jar files) created via the above script:

    $ tree -fl mlib

    or 

    $ cmd //c "tree /f /a mlib"    (for Windows users)
    
You will notice that the module `org.astro` is packaged to indicate that its version is `1.0`. Module `com.greetings` has been packaged to indicate that its main class is `com.greetings.Main`. Module `com.greetings` can be executed without needing to specify its main class (`Main.class`).
    
And we run the example with the following command:
    
    $ ./run.sh
    
Check the contents of all of these script files (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.
