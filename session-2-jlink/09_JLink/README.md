## jlink

Another one of the new tools that Jigsaw brings is 'jlink' which is a tool to produce custom run-time images,
a single artefact containing both Java and the modules being run.

### Scripts to run

1. `compile.sh` - Comiles the hello world example
2. `packing.sh` - Creates JAR modules out of the class files
3. `link.sh` - Run jlink to produce a new run-time image for the hello world example
4. `run.sh` - Run the new run-time image.
