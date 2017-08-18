# ServiceMonitor - Migration - Cheatsheet

Disclaimer: do not follow the steps below literally, in case of issues try to debug. If you think it is an issue, report back in the feedback form or ask on the IRC channel.

Enjoy the migration journey!

Notes:

    - open packages and modules (for reflection)
      - unnamed and automatic modules (for migration)
          -unnamed modules
              - all jars on the classpath
              - has no name
              - can read all modules (all JDK modules)
              - exports all packages

  
The application is a Java 8 app, try the following:

   To build:
    
        mvn clean install
        
   To run:
   
        java -cp 'monitor/target/libs/*':'monitor/target/main-1.0-SNAPSHOT.jar' monitor.Main
    
Migration steps:

- Switch maven to use Java 9
    - create a file called `.mavenrc` in the `$HOME` directory 
    - add `JAVA_HOME=/path/to/java-9` to it and save it   

- Make the project a Java 9 (from Java 8)	
   - change pom.xml
        - option 1:
            change the java source and target versions in the pom.xml
            
        - option 2:
            create a profile for maven to detect java 9 and activate the profile

        - in the profile add the below:

                <properties>
                    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                     <maven.compiler.source>1.9</maven.compiler.source>
                     <maven.compiler.target>1.9</maven.compiler.target>
                </properties>

        option 3: 
            use the new `release` tag in Java 9 (amend the pom.xml file)

    hint: play around with the version number format, try using both `1.9` and `9` and build the package 

   - Build fails:
        
            cannot find symbol
            [ERROR] symbol: class BASE64Encoder
          

   - Load project into IDE and go to the class reported the compiler
   - In IntelliJ, Add the folder `03_ServiceMonitor_migration_to_java_9` as a module via File > New > Module from Existing source (check search project recursively, and click on Next till you navigate till Finish, IntelliJ detects it to be a Java project)
   - Find the reference to the package in the source file, maybe it needs to be removed due to removal of access to Internals API in Java 9 
   - Find out what the new replacement access to this API is, hint: class `Base64`
    - re-run `mvn clean install` inside the `monitor.utils` module

   - Problems
        - Warnings in `monitor.utils.UtilsTest`
            - caused by `Mockito`
                - caused by `ByteBuddy`
        
Notes/hints:

        Static access to Internal API is available
        Reflective access to Internal API results in a warning like the one shown
        But opening the package using the right command-line flag with javac can help make reflective access to the packages

   - Go back to the `pom.xml` of the module where the test failed and create the same profile as done above
   - Ask the `surefire` plugin to allow opening the `java.lang` package in the `java.base` module (hint: `argLine`, `--add-opens ...`). We do not have any modules in our application hence use the notation that allows it to open to all modules name or not.

   Alternatives: 
        `--add-export` (find out difference between `--add-opens` and `--add-exports`)
        `--add-opens`: reflective access
        `--add-exports`: access to internal API (public types are accessible)

   Try to play with the `--illegal-access=deny` command-line flag to see what the compiler throws back at us.

   You can also make it such that you open access to the list of packages your application needs and then close access (hint: try to combine the above flags and see what happens)

   The whole problem on the warnings thrown by maven on the test(s) could have been avoided by updating the supporting test class that was giving an issue i.e. `Mockito` to the latest version (or at least `2.8.47`) which has an updated `bytebuddy` in it. 

   - Re-run `mvn clean install` inside the `monitor.utils` module

   - Now move to the parent module to rebuild the whole project using `mvn clean install`

   - Compilation failures when compiling monitor.rest:
        
        `monitor/rest/MonitorServer.java:[9,16] error: package javax.xml.bind is not visible`

     and 

        
          monitor/rest/StatisticsEntity.java:[6,23] error: cannot find symbol
          [ERROR]   symbol:   class Generated
          [ERROR]   location: package javax.annotation
          [ERROR] /home/satyasai/git-repos/jdk9-jigsaw/session-4-refactoring-migration/03_ServiceMonitor_migration_to_java_9/monitor.rest/src/main/java/monitor/rest/StatisticsEntity.java:[7,21] error: package javax.xml.bind.annotation is not visible
        
   - Missing Java EE modules have to be added manually at compile time (hint: maven compiler ...) - fixes the compiler error on `javax.xml.bind`

Notes:

    Splitting packages
    - two modules cannot contain the same packages
        - this is checked for in named modules
        - this is NOT check for unname modules (compatibality reasons, otherwise restricts older apps) 
        - classes in the unnamed module/package are invisible

    Mend:
        use --upgrade-module-path
        use --patch-module


   - Fix the `@Generated` annotation in `java.xml...`
   - But you get a `@NonNull` annotation errors, due to split-package and `@NonNull` not available

   - Fix this issue using the `--patch-module` flag, hint:
     `java.xml.ws.annotation=${settings.localRepository}/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar}`

   - re-run `mvn clean install`, but you get this error (if you used the release option in the maven compiler):
       
         error: patching system module java.xml.ws.annotation is not allowed in combination with --release
       

   - Run the application
    
         java -cp 'monitor/target/libs/*':'monitor/target/main-1.0-SNAPSHOT.jar' monitor.Main

   - You get `ClassCastException`:
        Due to the fact, in Java 9, returns a different type of a classload class by the `[Class].class.getClassLoad()` call. If you can't find a solution, comment out the method leading to this error in the `main()` method.

   - You get `NoClassDefFoundErr` for class `javax/xml/bind/JAXBException`, hint: pass it in as a argument to the `java` command, use `--add-modules`, to add the required `jdk` equivalent module(s) 

If all of this does not make sense, watch the first half of Nicolai Parlog's video on [From Jar hell and Back](https://youtu.be/NKY2FYTCo7I).
