### Multi-module compilation example

In this example modules are compiled at the same time unlike with the previous example where the compilation happens sequentially.

**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

Perform the below commands to see the contents of the respective sources contained in the `src` folder:
    
    $ tree -fl src

    or 

    $ cmd //c "tree /f /a src"    (for Windows users)

The sources are compiled into the folders `mods/org.astro` and `mods/com.greetings` with the following commands:

    $ ./multiModCompile.sh

Perform the below command to see both the modules created via the above script:

    $ find mods -type f
    
And we run the example with the following command:
    
    $ ./run.sh
    
Check the contents of all these script files (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.
