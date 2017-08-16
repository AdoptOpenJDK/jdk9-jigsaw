# Building Java 9 Modules using Gradle (from monolith to modular)

This is a guide from the creators of Gradle on how to migrate a monolith Java application into a multi-module Java 9 application using Gradle as the build tool.

You will create gradle tasks that will invoke the various `java` or `javac` commands passing the respective arguments to it.

### What you’ll learn

One of the most exciting features of Java 9 is its support for developing and deploying modular Java software. In this guide, you’ll learn exactly what you need to change in your Java application to:

- produce Java 9 modules for your java libraries.

- consume Java 9 modules as your dependencies.

- use Java’s ServiceLoader pattern with Java 9 modules.

- run an application using Java 9 modules.

- use an experimental plugin to do all of this more simply.

While Gradle version 4.1-rc-2 doesn’t have first-class support for Java 9 modules yet, this guide shows you how to experiment with them before that support is complete.

---

Please continue to [https://guides.gradle.org/building-java-9-modules/](https://guides.gradle.org/building-java-9-modules/), for instructions on prerequisites, setup, etc....
