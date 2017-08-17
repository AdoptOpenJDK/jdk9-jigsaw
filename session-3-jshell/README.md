# JShell

Java REPL (Read-Eval-Print-Loop): previously called Kulla.

The JShell API can be used via applications as well.

Windows Users

Check if `JAVA_HOME` is set on your machine

    c:\... echo %JAVA_HOME%
    "c:\Program Files\Java\jdk-9\"

Look for the JShell REPL in the `JAVA_HOME\bin` folder:
    
    c:\... dir /W %JAVA_HOME%\bin\js*
    
    Directory of C:\Program Files\Java\jdk-9\bin

    jshell.exe     jsound.dll     jsoundds.dll   jstack.exe     jstat.exe
    jstatd.exe
    
And locate the command from the above list. Ensure `JAVA_HOME\bin` is in your PATH.

    c:\... echo %PATH%
    c:\Program Files\Java\jdk-9\bin\;....
     
If `JAVA_HOME` is not set on your Windows machine (or it's not set to Java 9) or `PATH` doesn't show the bin folder for JDK-9, You should set the correct value by following these steps:

- Invoke the Control Panel, and then System.
- Click Advanced and then Environment Variables.
- Add the location of the JDK-9 folder to the `JAVA_HOME`,
- and add or edit the location of the bin folder of the JDK installation to `PATH` variable in System Variables.

You should set the `JAVA_HOME` value between quotation marks.
       
Once `JAVA_HOME` and `PATH` are set, you must close the current console and then open a new Windows Console. Now you are ready to start JShell with the following command:

    c:\... jshell
     
MacOs / Linux Users

Look for the JShell REPL in the `JAVA_HOME/bin` folder:
    
    $ ls $JAVA_HOME/bin/

    appletviewer  java     javapackager  jdb        jinfo  jps         jstatd        policytool   serialver   wsimport
    idlj          javac    javaws        jdeprscan  jjs    jrunscript  jweblauncher  rmic         servertool  xjc
    jaotc         javadoc  jcmd          jdeps      jlink  [jshell]    keytool       rmid         tnameserv
    jar           javah    jconsole      jhsdb      jmap   jstack      orbd          rmiregistry  unpack200
    jarsigner     javap    jcontrol      jimage     jmod   jstat       pack200       schemagen    wsgen
    
And locate the command from the above list. Ensure `JAVA_HOME/bin` is in your `PATH`.

    $ echo $PATH
    /usr/lib/jvm/jdk-9/bin/:....

If `JAVA_HOME` is not set on your Mac or Linux machine (or it's not set to Java 9) or `PATH` doesn't show the bin folder for JDK-9, You should set the correct values by following the next steps:
(For Mac Users the JDK 9 path could be /Library/Java/JavaVirtualMachines/jdk9/Contents/Home)

    $ export JAVA_HOME=/usr/lib/jvm/jdk-9/
    $ export PATH=/usr/lib/jvm/jdk-9/bin:$PATH

Once `JAVA_HOME` and `PATH` are set you can start JShell with the next command
    
    $ jshell

    
After you start JShell (Windows, MacOs and Linux Users) you get taken to the JShell prompt:
    
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
    JShell shell = JShell.create();
    shell.eval("int x = 5;");
    Stream<VarSnippet> vars = shell.variables();
```

The above block is not for the REPL.
    
Some more Java 9 improvements to try in JShell (thanks to @sjmaple for the contribution)

    jshell> import java.time.*;
    jshell> void printf(Object a) { 
        System.out.println(a); 
    }

    |  created method printf(Object)
    
Collections of factory methods: 

1-10 arguments + varargs: 

    jshell> List.of(1, 2, 3, 4, 5)
    $3 ==> [1, 2, 3, 4, 5]
    
    jshell> Map.of("hello", "world", "status", "mad")
    $4 ==> {hello=world, status=mad}
    
    jshell> Set.of(1,2,4)
    $5 ==> [2, 4, 1]

    jshell> Set.of(1,2,4)      <=== no change in order of the elements
    $6 ==> [2, 4, 1]
    
Implementation classes 2 elements vs N elements:
    
    jshell> List.of(1).getClass()
    $9 ==> class java.util.ImmutableCollections$List1

    jshell> List.of(1, 2).getClass()
    $10 ==> class java.util.ImmutableCollections$List2

Streams: 
   * dropwhile/takewhile:
   
    jshell> IntStream.range(1, 10).dropWhile(x -> x < 5).forEach(System.out::println)
    
    5
    6
    7
    8
    9

   * iterate 

    jshell> IntStream.iterate(0, x -> x + 1).filter(x -> x < 10).forEach(System.out::println)

    0
    1
    2
    3
    4
    5
    6
    7
    8
    9

   Press Ctrl-C to get back to the `jshell` prompt
     
   * Stream from Optional: 
    
    jshell> Optional.of(1).stream().map(x -> x*3);
    $16 ==> java.util.stream.ReferencePipeline$3@4e7dc304
     
    jshell> Optional.empty().or(() -> Optional.of("Hello"))
    $17 ==> Optional[Hello]
    
    jshell> Optional.empty().ifPresent(null) 
    
    jshell> Optional.empty().ifPresentOrElse(null, () -> printf("hello"));
    hello
 
   * Date and time improvements:
     
    jshell> import java.time.*;
    
    jshell> LocalDate.now()
    $21 ==> 2017-08-15

    jshell> $21.datesUntil(LocalDate.of(2017, 8, 20)).forEach(System.out::println)
    2017-08-15
    2017-08-16
    2017-08-17
    2017-08-18
    2017-08-19
    
Concurrency
   * Completable futures:
     
    jshell> CompletableFuture<String> future = new CompletableFuture<>()
    future ==> java.util.concurrent.CompletableFuture@77846d2c[Not completed]

    jshell> future.copy()
    $28 ==> java.util.concurrent.CompletableFuture@16f7c8c1[Not completed]
    
    jshell> $28.complete("Hello world")
    $29 ==> true
    
    jshell> $29.get()
    |  Error:
    |  boolean cannot be dereferenced
    |  $29.get()
    |  ^-----^

    jshell> future.isDone()
    $30 ==> false
    
   * delays / timeouts:
    
    * completeOnTimeout(T value, long timeout, TimeUnit unit)
   
        jshell> future.completeOnTimeout("Isn't this amazing", 1, TimeUnit.SECONDS)
        $31 ==> java.util.concurrent.CompletableFuture@281e3708[Not completed, 2 dependents]
     
    * orTimeout(1, TimeUnit.SECONDS)
     
        jshell> future.orTimeout(1, TimeUnit.SECONDS)
        $32 ==> java.util.concurrent.CompletableFuture@281e3708[Completed normally]
     
HttpClient

   Run jshell with a CLI argument to load a JDK module (`jdk.incubator.httpclient`)
   
    $ jshell --add-modules jdk.incubator.httpclient
    
    jshell> import jdk.incubator.http.*;
    jshell> String body = HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("https://www.google.com")).GET().build(), HttpResponse.BodyHandler.asString()).body();

Try using http in the URL address? What is the expected result? 

    jshell> String body = HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("http://www.google.com")).GET().build(), HttpResponse.BodyHandler.asString()).body();
    body ==> "<HTML><HEAD><meta http-equiv=\"content-type\" co ... A>.\r\n</BODY></HTML>\r\n"
    
Try without https in the URL address? What is the expected result? 
    
    java.lang.NullPointerException

Now that we can make `http` calls via the REPL, lets have a look at how that works. We can use Java 9's new API to create `Http` calls. Check the `JavaDoc` for `HttpRequest` or use the example above.
Make 50 `http` `GET` calls to http://www.google.com via the REPL. 

Process management API: 
    
   Reload jshell using the usual CLI invocation:
   
    $ jshell
    
    jshell> ProcessHandle currentProcess = ProcessHandle.current();
    currentProcess ==> 10420

    jshell> System.out.println("Current Process Id: = " + currentProcess.pid());
    Current Process Id: = 10420
           
   * ProcessHandle.Info info()
       * Optional<String[]> arguments()
       * Optional<String> command()
       * Optional<String> commandLine()
       * Optional<Instant> startInstant()
       * Optional<Duration> totalCpuDuration()
       * Optional<String> user()
        
    jshell> currentProcess.info()
    $5 ==> [user: Optional[username], cmd: /usr/lib/jvm/jdk-9/bin/java, args: [-agentlib:jdwp=transport=dt_socket,address=localhost:40499, jdk.jshell.execution.RemoteExecutionControl, 41667], startTime: Optional[2017-08-15T17:36:30.240Z], totalTime: Optional[PT0.88S]]
 
   * Find JShell process with jps
   
    jshell> ProcessHandle currentProcess = ProcessHandle.current();
    currentProcess ==> 10420
    
    jshell> currentProcess.pid()
    $7 ==> 10420
    
    jshell> ProcessHandle.of(10420)
    $9 ==> Optional[10420]

    jshell> $9.get()
    $10 ==> 10420

    jshell> $10.info()
    $11 ==> [user: Optional[username], cmd: /usr/lib/jvm/jdk-9/bin/java, args: [-agentlib:jdwp=transport=dt_socket,address=localhost:40499, jdk.jshell.execution.RemoteExecutionControl, 41667], startTime: Optional[2017-08-15T17:36:30.240Z], totalTime: Optional[PT1.09S]]


StackWalker:
    
    jshell> StackWalker.getInstance().walk(s -> s.limit(5).collect(Collectors.toList()));
    [do_it$(java:15), jdk.jshell/jdk.jshell.execution.DirectExecutionControl.invoke(DirectExecutionControl.java:209), jdk.jshell/jdk.jshell.execution.RemoteExecutionControl.invoke(RemoteExecutionControl.java:116), jdk.jshell/jdk.jshell.execution.DirectExecutionControl.invoke(DirectExecutionControl.java:119), jdk.jshell/jdk.jshell.execution.ExecutionControlForwarder.processCommand(ExecutionControlForwarder.java:134)]

  Stop doing new Exception when you need to inspect the stack while in the REPL. 
     
JDK structure changes
    
   * Semantic versioning: 
   
    jshell> Runtime.version()
    $13 ==> 9-ea+170-jigsaw-nightly-h6410-20170518
    
    jshell> $13.major()
    $14 ==> 9
    
    jshell> $13.minor()
    $15 ==> 0
    
Language improvements continue (Project Coin):

   _ is an invalid identifier (for lambdas)
   
     jshell> int _ = 1
     |  Error:
     |  as of release 9, '_' is a keyword, and may not be used as an identifier
     |  int _ = 1
     |      ^
     |  Error:
     |  reached end of file while parsing
     |  int _ = 1
     |           ^

     jshell> int __ = 1
     __ ==> 1
    
   private methods in interfaces:
       
     jshell> interface A { private int zero() { return 0;} default int one() { return zero() + 1;}}
     |  created interface A
   
   effectively final in try with resources:
       
     jshell> boolean a() throws Exception { Socket s = new Socket(); return s.isClosed(); }
     |  created method a()

     jshell> boolean a() throws Exception { Socket s = new Socket(); try (s) { } return s.isClosed(); }
     |  modified method a()

UTF-8 property files:
    Create a text file call `properties.properties` containing, save the file and load `jshell`:

        # This is the default properties.properties file
        s1 = computer
        s2 = disk
        s3 = monitor
        s4 = keyboard

     jshell> new PropertyResourceBundle(new FileInputStream("properties.properties")).handleGetObject("s2")
     $1 ==> "computer"    
    
Finally, exit the REPL via one of the two ways:

    jshell> /exit
    |  Goodbye
     
Or
     
    jshell> [Ctrl][D]     
    (quietly exits)
 
Read all about the Java REPL at 
- Project Kulla: https://adoptopenjdk.gitbooks.io/adoptopenjdk-getting-started-kit/content/en/openjdk-projects/kulla/kulla.html

Follow Hendrik's hands-on presentation on JShell to learn about intermediate and advanced features:
https://youtu.be/y868lMk6NtY?t=13156

Try some more JShell examples:
- https://zeroturnaround.com/rebellabs/the-best-java-9-language-and-api-improvements/
- http://jakubdziworski.github.io/java/2016/07/31/jshell-getting-started-examples.html
- REPL_Tutorial.pdf - https://community.oracle.com/docs/DOC-1016569
- https://www.infoq.com/articles/Java9-New-HTTP-2-and-REPL (see towards the bottom of the blog post)

Some online Java REPLs to play with:
- http://www.javarepl.com/term.html
- https://github.com/albertlatacz/java-repl
- https://repl.it/languages/java
- https://teamtreehouse.com/community/installing-java-repl

Credits
- Contributions from Hendrik Ebbers ([@hendrikebbers](http://github.com/hendrikebbers)) has been distilled above - thanks [@hendrikebbers](http://github.com/hendrikebbers)
