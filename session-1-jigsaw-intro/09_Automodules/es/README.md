### External libraries ejemplo

En este ejemplo incluimos librerias externas, las cuales no han sido aún modularizadas.
Estos archivos jar (jar files) pueden ser convertidos en lo que se llama *[módulos automáticos](*[automatic modules](The State of the Module System) The State of the Module System))* por `javac`.
Para tener una idea de como estos módulos lucirán y cuales dependencias ellos van a requerir usted puede utilizar la herramienta `jar`:

    $ jar -d --file=lib/junit-4.12.jar
    No module descriptor found. Derived automatic module.
    
    module junit@4.12 (automatic)
      requires mandated java.base
       contains junit.extensions
       contains junit.framework
       contains junit.runner
       contains junit.textui
       contains org.junit
       ...

Esta salida nos dice, que el módulo automático será llamado `junit`  y va a tener el número de versión 4.12.
Desafortunadamente no nos dice, que JUnit depende de Hamcrest.
Nosotros podemos obtener mas informacion utilizando `jdeps`:

    $ jdeps -s lib/junit-4.12.jar 
    junit-4.12.jar -> java.base
    junit-4.12.jar -> java.management
    junit-4.12.jar -> not found

La última entrada `not found` nos dice que hay una dependencia desconocida.
La opción `-s` en la instrucción anterior solicita que solo sea mostrado el resumen.
Para ver mas detalles, vamos a omitir la opción `-s` así:

    $ jdeps lib/junit-4.12.jar | grep "not found"
    junit-4.12.jar -> not found
       org.junit                                          -> org.hamcrest                                       not found
       org.junit.experimental.results                     -> org.hamcrest                                       not found
       org.junit.internal                                 -> org.hamcrest                                       not found
       org.junit.internal.matchers                        -> org.hamcrest                                       not found
       org.junit.matchers                                 -> org.hamcrest                                       not found
       org.junit.matchers                                 -> org.hamcrest.core                                  not found
       org.junit.rules                                    -> org.hamcrest                                       not found


Aha! Hamcrest es mostrado. Y también  se ve, que no hay otras dependencias desconocidas de Junit. Vamos a verificar Hamcrest para estar seguros:

    $ jar -d --file=lib/hamcrest-core-1.3.jar
    o module descriptor found. Derived automatic module.
    
    module hamcrest.core@1.3 (automatic)
      requires mandated java.base
      contains org.hamcrest
      contains org.hamcrest.core
      contains org.hamcrest.internal

y

    $ jdeps -s lib/hamcrest-core-1.3.jar
    hamcrest-core-1.3.jar -> java.base

Bien, Hamcrest no tiene más dependencias.

Ejecute los siguientes comandos para ver el contenido de los códigos fuente contenidos en el directorio `src` :

    $ cat src/com.greetings/module-info.java
    $ cat src/com.greetings/main/java/com/greetings/Main.java
    $ cat src/com.greetings/main/java/com/greetings/Greet.java
    $ cat src/com.greetings/test/java/com/greetings/GreetTest.java 

Como usted puede ver en el `module-info.java`, hemos adicionado una dependencia para JUnit utilizando el nombre de módulo devuelto en los comandos ejecutados con anterioridad.
Hamcrest es una dependencia transitiva (transitive dependency) y no tiene que ser adicionada de manera separada.
También, desde JUnit tendremos acceso a nuestras clases para ejecutar pruebas, nosotros exportamos`com.greetings` para el módulo JUnit.


**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la sección [Descargue e instale los comandos `tree` y `wget`](../../../es/README.md) en el documento README.md y aplique la solución apropiada.

Usted puede ver la información de dependencias ejeuctando el comando:

    $ ./deps.sh

Intente compilar los módulos utilizando el siguiente comando:

    $ ./compile.sh
    
Y ejecute los ejemplos con el siguiente comando:
    
    $ ./run.sh
    
Deberiamos obtener la siguiente salida:

```
     *** Running from within the mods folder without arguments. *** 
    
    Hello World!
    
     *** Running from within the mods folder with arguments. *** 
    
    Hello Alice!
    Hello Bob!
    Hello Charlie!
    
     *** Running tests *** 
    
    JUnit version 4.12
    .....
    Time: 0.009
    
    OK (5 tests)

```
    
Revise el contenido the los scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

Vea [../../01_Greetings/es/README.md](../../01_Greetings/es/README.md) para aprender mas acerca de la convención de nombres de paquete y módulos y como evitar confusiones.
