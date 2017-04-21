### The Linker (jlink) example

Another one of the new tools that Jigsaw brings is 'jlink' which is a tool to produce custom run-time images, a single artefact containing both Java and the modules being run.

`jlink` is the linker tool and can be used to link a set of modules, along with their transitive dependencies, to create a custom modular run-time image (see JEP 220). The tool currently requires that modules on the module path be packaged in modular JAR or JMOD format.

In example creates a run-time image that contains the module `com.greetings` and its transitive dependencies:
    
The directory `mlib` on the module path contains the artifact for module `com.greetings`.

**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

Compile the 'hello world' example using:

    $ ./compile.sh
    
Create JAR modules out of the class files:
    
    $ ./packing.sh

Run `jlink` to produce a new run-time image (distributable JDK + your app) for the 'hello world' example:

    $ ./link.sh

Run the new run-time image (distributable JDK + your app):
    
    $ ./run.sh
    
Inspect the images / artifacts created in the above process:
    
    $ ./inspect.sh
    
Check the contents of all these script files (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.
Especially in the `run.sh` script, as this one reveals some interesting details about distributable JDK binaries + modules, module images.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.

The `jlink` tool supports many advanced options to customize the generated image, see `jlink --help` for more options.
`$JAVA_HOME/jmods` is the directory containing `java.base.jmod` and the other standard and JDK modules. 
If you using your own build of OpenJDK then the `jmod` files are in `$BUILDOUTPUT/images/jmods`, where `$BUILDOUTPUT` is the build output directory.

We will also see the `jimage` command in this example and learn how to create a JDK image with a smaller footprint.
