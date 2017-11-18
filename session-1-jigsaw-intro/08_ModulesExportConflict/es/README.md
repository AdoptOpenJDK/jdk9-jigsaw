### Modules exports conflict example

In this example we mistakenly export the same package `org.astro` from two different modules (`org.astro` & `org.astro2`) via our module declaration (in `module-info.java`).
   
Perform the below commands to see the contents of the respective sources contained in the `src` folder:

    $ cat src/com.greetings/module-info.java
    $ cat src/com.greetings/com/greetings/Main.java
    
    $ cat src/org.astro/module-info.java
    $ cat src/org.astro/org/astro/World.java
          
    $ cat src/org.astro2/module-info.java
    $ cat src/org.astro2/org/astro/World.java
                
**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

Try to compile the modules using the below command:

    $ ./compile.sh
    
And we run the example with the following command:
    
    $ ./run.sh
    
We should get a similar run-time error:

```
    Error occurred during initialization of VM
    java.lang.module.FindException: Two versions of module org.astro found in mods (org.astro and org.astro2)
    	at jdk.internal.module.ModulePath.scanDirectory(java.base@9-ea/ModulePath.java:294)
    	at jdk.internal.module.ModulePath.scan(java.base@9-ea/ModulePath.java:232)
    	at jdk.internal.module.ModulePath.scanNextEntry(java.base@9-ea/ModulePath.java:190)
    	at jdk.internal.module.ModulePath.find(java.base@9-ea/ModulePath.java:154)
    	at java.lang.module.ModuleFinder$2.lambda$find$0(java.base@9-ea/ModuleFinder.java:368)
    	at java.util.stream.ReferencePipeline$3$1.accept(java.base@9-ea/ReferencePipeline.java:195)
    	at java.util.AbstractList$RandomAccessSpliterator.tryAdvance(java.base@9-ea/AbstractList.java:706)
    	at java.util.stream.ReferencePipeline.forEachWithCancel(java.base@9-ea/ReferencePipeline.java:127)
    	at java.util.stream.AbstractPipeline.copyIntoWithCancel(java.base@9-ea/AbstractPipeline.java:502)
    	at java.util.stream.AbstractPipeline.copyInto(java.base@9-ea/AbstractPipeline.java:488)
    	at java.util.stream.AbstractPipeline.wrapAndCopyInto(java.base@9-ea/AbstractPipeline.java:474)
    	at java.util.stream.FindOps$FindOp.evaluateSequential(java.base@9-ea/FindOps.java:152)
    	at java.util.stream.AbstractPipeline.evaluate(java.base@9-ea/AbstractPipeline.java:234)
    	at java.util.stream.ReferencePipeline.findFirst(java.base@9-ea/ReferencePipeline.java:476)
    	at java.lang.module.ModuleFinder$2.find(java.base@9-ea/ModuleFinder.java:370)
    	at java.lang.module.Resolver.findWithBeforeFinder(java.base@9-ea/Resolver.java:856)
    	at java.lang.module.Resolver.resolve(java.base@9-ea/Resolver.java:144)
    	at java.lang.module.Configuration.resolveAndBind(java.base@9-ea/Configuration.java:257)
    	at java.lang.module.ModuleDescriptor$1.resolveAndBind(java.base@9-ea/ModuleDescriptor.java:2792)
    	at jdk.internal.module.ModuleBootstrap.boot(java.base@9-ea/ModuleBootstrap.java:279)
    	at java.lang.System.initPhase2(java.base@9-ea/System.java:1928)

```
    
Check the contents of this script file (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.
