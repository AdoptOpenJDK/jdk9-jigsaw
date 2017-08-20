# bach - Java Shell Builder

This repository shows how you can use JShell to build your (modular) projects using JShell code snippets or even plain Java syntax.
**Every** platform that sports a `jshell` executable is able to compile and run your build script out-of-the-box.
This includes local consoles in interactive and batch modes, CI environments and IDEs.
Use the debug features of your IDE to deep-dive into the details how **bach** works internally.  

See https://github.com/sormuras/bach for details.

Note: you should first work through the exercises and comments of the originating `session-1-jigsaw-intro` session.

## How to use it

The following examples demonstrate how to convert/port files like `compile.sh` or `run.sh` into jshell scripts (`.jsh`) or even into 100% Java classes (`.java`) that can be executed from within your IDE of choice.

### Example I

*Based on: "Session 1 - Example 1 - Greetings"*

The first example provided an almost 1:1 port of `compile.sh` located in directory `session-1-jigsaw-intro/01_Greetings`.
Again, read and understand the source and comments provided there first.
These explanations are not repeated here to keep the focus on **bach**.

Start from console with:

- `jshell build-session-1-jigsaw-intro-01_Greetings.jsh`

### Example II 

*Based on: "Session 1 - Example 3 - Multi Module Compilation"*

The second example show-cases the two worlds work together well: write your build *script* in a 100% Java class.
Use any class provided by the JDK and mount `Bach.java` as a classpath dependency.
Run and debug the build like usual in your IDE.

See `session-1-jigsaw-intro/Build_Session_1_JigsawIntro_03_MultiModuleCompilation.java` for an example.

Start from IDE:

- Execute class `Build_Session_1_JigsawIntro_03_MultiModuleCompilation` from the default package.
You only need to add the directories `bach` and `session-1-jigsaw-intro` as source folders and make sure the IDE uses this repository as the working directory.
In IDEA you could use `$MODULE_DIR$/session-3-jshell/bach-building-with-jshell`.

Start from console with:

- `jshell build-session-1-jigsaw-intro-03_MultiModuleCompilation.jsh`
