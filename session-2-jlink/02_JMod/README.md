### The JMod example

This example shows how to use `jmod`, `jmod` is a tool used to package modules or package jars into a compress file which is still different from a jar format.

It is similar to the `jimage` format but not distributed run-time, its not certain of its application yet.

**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

Modules involved just like in the previous example:
```
    com.greetings
    org.astro
```

Both the modules are compiled together from the sources into the folder `mods` with the following commands:

    $ ./compile.sh
    
And we run the `JMod` command with the following command:
    
    $ ./runJMod.sh
    
Check the contents of both these script files (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.
