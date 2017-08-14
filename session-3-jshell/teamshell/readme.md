# JShell Remote


This repository provides remote access to the JShell features of Java 9. It contains a [Spring Boot server](https://projects.spring.io/spring-boot/)
and a [Google Polymer](https://www.polymer-project.org) based client. The client can be used to execute commands in a server side JShell instance.
For communication between client and server the Open Source [Dolphin Platform](https://github.com/canoo/dolphin-platform) framework is used.

## How to use it

The Polymer clients uses [Bower](https://bower.io) to manage all dependencies. All client dependencies are already
commited to the repo so a developer do not need to install Bower to use the sample.

The project is defined as a Maven project and can easily opended in an IntelliJ 2017 preview. I'm using the following
versions on my system:

```
IntelliJ IDEA 2017.1.1
Build #IC-171.4073.35, built on April 6, 2017
JRE: 1.8.0_112-release-736-b16 x86_64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Mac OS X 10.11.6
```

To start the server you can easily start the `com.guigarage.shell.ShellServer` class. Since this project to not fit all
the needs for [Jigsaw](http://openjdk.java.net/projects/jigsaw/) you need to start the server by adding the following JVM flags:
```
--permit-illegal-access --add-modules java.xml.bind
```
You can find more information about that flags [here](https://www.sitepoint.com/reflection-vs-encapsulation-in-the-java-module-system/).