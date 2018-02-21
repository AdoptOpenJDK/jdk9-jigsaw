### Creating a multirelease JAR file

In this example we are going to create a multirelease JAR file that provides different implementation for the same class that are used with different JDK versions (8 and 9 in the particular example).

In the source folder there are two projects with similar structure. You can incorporate different strategies for compiling a multirelease JAR file from sources but in this example we are going to use a fairly straigforward one - we are going to compile the two projects and build the multirelease JAR from a combination of the class files in the two projects.

Create a new class ProcessManager in the Project_JDK9 project with the same API as the ProcessManager class in the Project_JDK8 project that lists all operating system processes. Use the utilities provided by the new ProcessHandle class in JDK9.

Use the `compile.sh` script to compile the projects (each project must be compiled using different JDK version but for this example we compiling both projects with JDK9 compiler - modify the `compile.js` script accordingly to compile the separate project with JDK8 installation on your workstation):

    $ ./compile.sh

Use the command below to create the multirelease JAR file:

    $ jar --create --file multirelease.jar -C src/Project_JDK8/target/classes . --release 9  -C src/Project_JDK9/target/classes .

**Note:** The important bit here is the `--release` argument that provides the compiled version of the class for the specific JDK release we target.

And we run the JAR with the `run.sh` script against different JDK8 and JDK9 and you can inspect the difference:
	    
    $ ./run.sh
    