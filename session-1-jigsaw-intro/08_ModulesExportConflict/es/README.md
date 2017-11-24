### Modules exports conflict ejemplo

En este ejemplo por error exportamos el mismo paquete `org.astro` en dos diferentes módulos (`org.astro` & `org.astro2`) en nuestra declaración del módulo (en `module-info.java`).
   
Ejecute los siguientes comandos para ver el contenido de cada clase Java contenida en el directorio `src`:

    $ cat src/com.greetings/module-info.java
    $ cat src/com.greetings/com/greetings/Main.java
    
    $ cat src/org.astro/module-info.java
    $ cat src/org.astro/org/astro/World.java
          
    $ cat src/org.astro2/module-info.java
    $ cat src/org.astro2/org/astro/World.java
                
**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la sección [Descargue e instale los comandos `tree` y `wget`](../../../es/README.md) en el documento README.md y aplique la solución apropiada.

Intente compilar los módulos utilizando el comando a continuación:

    $ ./compile.sh
    
Y ejecute el ejemplo con el siguiente comando:
    
    $ ./run.sh
    
Obtendra un  (run-time error) error de ejecución similar al siguiente:

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
    
Revise el contenido the los scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

Vea [../../01_Greetings/es/README.md](../../01_Greetings/es/README.md) para aprender mas acerca de la convención de nombres de paquete y módulos y como evitar confusiones.

**Ver el ejemplo:**
Para ver los códigos fuentes de este ejemplo vaya a [el directorio 08_ModulesExportConflict](../), ahí encontrará la versión en Inglés del presente documento, el directorio `src` con los códigos fuentes, y algunos scripts que identificará con la extensión `.sh`.
