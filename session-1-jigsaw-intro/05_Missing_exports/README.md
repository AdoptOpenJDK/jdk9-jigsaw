### Missing exports example

In this example we mistakenly omit the `exports` from the `com.astro` module declaration (in `module-info.java`).

    $ cat src/org.astro/module-info.java
    
**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.
    
Try to compile the modules using the below command:

    $ ./compile.sh
    
Compilation fails with the below error:

```
    src/com.greetings/com/greetings/Main.java:3: error: package org.astro is not visible
    import org.astro.World;
              ^
      (package org.astro is declared in module org.astro, which does not export it)
    1 error
```
    
Check the contents of this script file (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.
