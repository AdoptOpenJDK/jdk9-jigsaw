### --patch-module ejemplo

Desarrolladores que usen las clases `java.util.concurrent` desde Doug Lea's CVS usarán para compilar sus códigos y desplegar sus clases con `-Xbootclasspath/p`.
`-Xbootclasspath/p` ha sido removido de Java, y en la modularización su reemplazo es la opción `--patch-module` para sobreescribir clases en un módulo. Esta tambien puede ser utilizada para aumenta los contenidos de un módulo. 
La opción `--patch-module` tamnbién es soportada por `javac` para compilar códigos "como si fueran" parte del módulo.

Ejecute los siguientes comandos para ver el contenido de los códigos fuente contenidos en el directorio `src`:

- módulo `java.base` en el directorio `mypatches`
    
    ```
    $ tree mypatches
    ```

- módulo `com.greetings` en el directorio `mods`
    
    ```
    $ tree mods
    ```

- módulo `java.base` en el directorio `src`

    ```
    $ cat src/java.base/java/util/concurrent/ConcurrentHashMap.java
    ```

- módulo `com.greetings` en el directorio `src`

    ```
    $ cat src/com.greetings/com/greetings/Main.java
    ```

**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la sección [Descargue e instale los comandos `tree` y `wget`](../../../es/README.md) en el documento README.md y aplique la solución apropiada.

Los módulos son compilados en los directorios `mypatch` y `mods` respectivamente con el siguiente comando:

    $ ./compile.sh
    
Y el ejemplo se ejecuta dos veces (con y sin el patched code) con el siguiente comando:
    
    $ ./run.sh
    
Revise el contenido the los scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

Vea [../../01_Greetings/es/README.md](../../01_Greetings/es/README.md) para aprender mas acerca de la convención de nombres de paquete y módulos y como evitar confusiones.

**Ver el ejemplo:**
Para ver los códigos fuentes de este ejemplo vaya a [el directorio 07_patch_ConcurrentHashMap](../), ahí encontrará la versión en Inglés del presente documento, el directorio `src` con los códigos fuentes, y algunos scripts que identificará con la extensión `.sh`.
