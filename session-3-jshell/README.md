# JShell

Java REPL (Read-Eval-Print-Loop): previously called Kulla.

The JShell API can be used via applications as well.

Look for the the JShell REPL in the `JAVA_HOME/bin` folder:

    $ ls $JAVA_HOME/bin/

    appletviewer  java     javapackager  jdb        jinfo  jps         jstatd        policytool   serialver   wsimport
    idlj          javac    javaws        jdeprscan  jjs    jrunscript  jweblauncher  rmic         servertool  xjc
    jaotc         javadoc  jcmd          jdeps      jlink  [jshell]    keytool       rmid         tnameserv
    jar           javah    jconsole      jhsdb      jmap   jstack      orbd          rmiregistry  unpack200
    jarsigner     javap    jcontrol      jimage     jmod   jstat       pack200       schemagen    wsgen
    
And locate the command from the above list. Ensure `JAVA_HOME/bin` is in your `PATH`.

    $ echo $PATH
    /usr/lib/jvm/jdk-9/bin/:....
    
    or 
    
    $ echo %PATH%
    c:\Program Files/jdk-9/bin\;....
        
    $ jshell
    
And you get taken to the JShell prompt:
    
    |  Welcome to JShell -- Version 9-ea
    |  For an introduction type: /help intro
    
    jshell> 

The first command to try in the REPL:

    jshell> /?
     

Let's assign a string to a variable and see its effect:
    
    jshell> String name = "ICELAND-JUG"
    name ==> "ICELAND-JUG"

Define a void method and execute it:

    jshell> void hello() { System.out.println("Hi " + name); }
    |  modified method hello()

    jshell> hello()
    Hi ICELAND-JUG

    jshell> hello();
    Hi ICELAND-JUG

As you can see there is no need to use a semi-colon at the end of single line commands in the REPL.

By default, the REPL loads with pre-defined imports:

    jshell> /i
    |    import java.io.*
    |    import java.math.*
    |    import java.net.*
    |    import java.nio.file.*
    |    import java.util.*
    |    import java.util.concurrent.*
    |    import java.util.function.*
    |    import java.util.prefs.*
    |    import java.util.regex.*
    |    import java.util.stream.*


Now create your own variables, method, etc.. and play with them in the REPL.

Try using the `/v` or the `/m` REPL commands, here's a sample output:

    jshell> /v
    |    String name = "ICELAND-JUG"
    |    int i = 10
    |    int j = 15
    |    int k = 25
    
    jshell> /m
    |    void hello()
    
Now try some more REPL commands, at the `jshell` prompt:

    /vars 
    /list
    /methods 
    /edit hello
    /imports

As mentioned earlier, Java 9 provides a JShell API that we can access from within our application:

```java
    JShell shell = JSheel.create();
    shell.eval("int x = 5;");
    Stream<VarSnippet> vars = shell.variables();
```

The above block is not for the REPL.

We can make `http` calls via the REPL, lets have a look at how that works. We can use Java 9's new API to create `Http` calls. Check the `JavaDoc` for `HttpRequest`.

Make 50 `http` `GET` calls to http://www.google.com via the REPL.


Finally, exit the REPL via one of the two ways:

    jshell> /exit
    |  Goodbye
     
Or
     
    jshell> [Ctrl][D]     
    (quietly exits)
   
Sampe repositories:   
https://github.com/guigarage/JShell-Examples
https://github.com/guigarage/shellFX
https://github.com/guigarage/teamshell   
   
Read all about the Java REPL at 
- Project Kulla: https://adoptopenjdk.gitbooks.io/adoptopenjdk-getting-started-kit/content/en/openjdk-projects/kulla/kulla.html

Try some more JShell examples:
- http://jakubdziworski.github.io/java/2016/07/31/jshell-getting-started-examples.html
- https://java.net/downloads/adoptopenjdk/REPL_Tutorial.pdf
- https://www.infoq.com/articles/Java9-New-HTTP-2-and-REPL (see towards the bottom of the blog post)

Some online Java REPLs to play with:
- http://www.javarepl.com/term.html
- https://github.com/albertlatacz/java-repl
- https://repl.it/languages/java
- https://teamtreehouse.com/community/installing-java-repl

Credits
- Contributions from Hendrik Ebbers ([@hendrikebbers](http://github.com/hendrikebbers)) has been distilled above - thanks [@hendrikebbers](http://github.com/hendrikebbers)
