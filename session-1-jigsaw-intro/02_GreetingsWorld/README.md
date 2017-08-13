### Greetings world example

This second example updates the module declaration to declare a dependency on module `org.astro`. Module `org.astro` exports the API package `org.astro`. 

**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

Perform the below commands to see the contents of the respective sources contained in the `src` folder:
    
    $ tree -fl src

    or 

    $ cmd //c "tree /f /a src"    (for Windows users)

Perform the below commands to see the contents of the respective Java classes contained in the `src` folder:

    $ cat src/org.astro/module-info.java

    $ cat src/org.astro/org/astro/World.java

Module `org.astro` exports the package `org.astro` (see `src/org.astro/module-info.java` for more details).

    $ cat src/com.greetings/module-info.java

    $ cat src/com.greetings/com/greetings/Main.java

Module `com.greetings` imports the package `org.astro` (see `src/com.greetings/module-info.java` for more details).

The sources are compiled into the folders `mods/org.astro` and `mods/com.greetings` with the following commands:

    $ ./compile.sh
    
And we run the example with the following command:
    
    $ ./run.sh
    
Check the contents of both these script files (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.
