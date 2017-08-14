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
    JShell shell = JShell.create();
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
    
Some more Java 9 improvements to try in JShell (thanks to @sjmaple for the contribution)

    import java.time.*;
    void printf(Object a) { System.out.println(a); }

Collections of factory methods: 

1-10 arguments + varargs: 

    List.of(1, 2, 3, 4, 5)
    Map.of("hello", "world", "status", "mad")
    Set.of(1,2,4)
    Set.of(1,2,4)      <=== look for the change in order of the elements
    
implementation classes 2 elements vs N elements:
    
    List.of(1).getClass()
    List.of(1, 2).getClass()

streams: 
   * dropwhile/takewhile:
   
    IntStream.range(1, 10).dropWhile(x -> x < 5).forEach(System.out::println)

   * iterate 

    IntStream.iterate(0, x -> x + 1, x -> x < 10).forEach(System.out::println)
  
   * Stream from Optional: 
    
    Optional.of(1).stream().map(x -> x*3); 
    Optional.empty().or(() -> Optional.of(“Hello”))
    Optional.empty().ifPresent(null) 
    Optional.empty().ifPresentOrElse(null, () -> printf(“hello“));
     
   * Date and time improvements:
     
    import java.time.*;
    LocalDate.now()
    $2.datesUntil(LocalDate.of(2017, 4, 1)).forEach(System.out::println)
    
Concurrency
   * Completable future:
     
    new CompletableFuture<String>() future = new CompletableFuture<>()
    future.copy()
    $47.complete("Hello world“)
    $47.get() <—true
    future.isDone() <— false
    future.completeOnTimeout("Isn't this amazing", 1, TimeUnit.SECONDS)
        
    delays / timeouts: 
       *  orTimeout(1, TimeUnit.SECONDS)
       *  completeOnTimeout(T value, long timeout, TimeUnit unit)
     
HttpClient

    import jdk.incubator.http.*;
    String body = HttpClient.newHttpClient().send(HttpRequest.newBuilder(URI.create("https://www.google.com")).GET().build(),HttpResponse.BodyHandler.asString()).body();
    
Try without http? What is the expected result? 
    
    java.lang.NullPointerException 

Process API: 
    
    ProcessHandle currentProcess = ProcessHandle.current();
    System.out.println("Current Process Id: = " + currentProcess.getPid());
    ProcessHandle ph =  Process.toHandle()
    Stream<ProcessHandle> children() / Stream<ProcessHandle> descendants()
    CompletableFuture<Process> onExit() <- do something when the process is finished. No more tears and third party libs.
    static Optional<ProcessHandle> of(long pid)
    
    ProcessHandle.Info info()
        Optional<String[]> arguments()
        Optional<String> command()
        Optional<String> commandLine()
        Optional<Instant> startInstant()
        Optional<Duration> totalCpuDuration()
        Optional<String> user()
    
   Find JShell process with jps
   
    ProcessHandle currentProcess = ProcessHandle.current();
    currentProcess.getPid()
    ProcessHandle.of(27468)
    $22.get()
    $23.info()

StackWalker:
    
    StackWalker.getInstance().walk(s -> s.limit(5).collect(Collectors.toList()));
    Stop doing new Exception when you need to inspect the stack. 
     
JDK structure changes
    
    no tools.jar 
    could be a problem if you require classes from it 
    
   Semantic versioning: 
   
    Runtime.version()
    $53.major()
    $53.minor()
    
project Coin, language improvements continue:

   _ is an invalid identifier (for lambdas)
   
       _ = 1
       __ = 1
    
   private methods in interfaces:
       
       interface A { private int zero() { return 0;} default int one() { return zero() + 1;}}
   
   effectively final in try with resources:
       
       boolean a() throws Exception { Socket s = new Socket(); return s.isClosed(); }
       boolean a() throws Exception { Socket s = new Socket(); try (s) { } return s.isClosed(); }

utf-8 property files:

    new PropertyResourceBundle(new FileInputStream("properties.properties"))
                              .handleGetObject("name")    
    
   
Read all about the Java REPL at 
- Project Kulla: https://adoptopenjdk.gitbooks.io/adoptopenjdk-getting-started-kit/content/en/openjdk-projects/kulla/kulla.html

Try some more JShell examples:
- http://jakubdziworski.github.io/java/2016/07/31/jshell-getting-started-examples.html
- REPL_Tutorial.pdf   https://community.oracle.com/docs/DOC-1016569
- https://www.infoq.com/articles/Java9-New-HTTP-2-and-REPL (see towards the bottom of the blog post)

Some online Java REPLs to play with:
- http://www.javarepl.com/term.html
- https://github.com/albertlatacz/java-repl
- https://repl.it/languages/java
- https://teamtreehouse.com/community/installing-java-repl

Credits
- Contributions from Hendrik Ebbers ([@hendrikebbers](http://github.com/hendrikebbers)) has been distilled above - thanks [@hendrikebbers](http://github.com/hendrikebbers)
