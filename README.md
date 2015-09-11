# jdk9-jigsaw

Examples of some of the features of Jigsaw released in the Early Access build of JDK9.

The examples here are directly from the Project Jigsaw: Module System Quick-Start Guide,
see [http://openjdk.java.net/projects/jigsaw/quick-start](http://openjdk.java.net/projects/jigsaw/quick-start).

## Examples covered
    - Greetings
    - Greetings world
    - Multi-module compilation
    - Packaging
    - Missing requires or missing exports
    - Services
    - The linker
    - javac -Xmodule and java -Xoverride

    Each example is enclosed in a folder of its own containing bash scripts to compile, 
    package and run the respective examples.

    Note: the bash files provided should work on Linux and in theory on the MacOS as well.
    For Windows we would have to manually convert the .sh files into .bat, with minor tweaks
    should also work there. Happy to received a pull request for it.

## Download (all platforms)
Early Access build of Jigsaw JDK9 is available at [https://jdk9.java.net/jigsaw/](https://jdk9.java.net/jigsaw/).

The bash script ```getJigsawJDK.sh``` helps download the latest Jigsaw JDK from Oracle.   
    
## Must reads
- [The State of the Module System](http://openjdk.java.net/projects/jigsaw/spec/sotms/)
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html) <br/>
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html)

## Other resources
- [Adopt OpenJDK homepage](https://adoptopenjdk.java.net/)
- [Adopt OpenJDK: Getting Started Kit](http://bit.ly/1NUkPWw)