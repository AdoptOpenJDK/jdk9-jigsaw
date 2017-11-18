### Packaging ejemplo

En los anteriores los contenido de los módulos compliados fueron creados en el sistema de archivos. Para transporte y despliegue usualmente es mas conveniente empaquetar los módulos como un JAR modular. 

Un JAR modular es un archivo JAR regular que tiene un `module-info.class` en su directorio raíz, el ejemplo crea `org.astro@1.0.jar` y `com.greetings.jar` en el directorio `mlib`.

**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la sección [Descargue e instale los comandos `tree` y `wget`](../../../es/README.md) en el documento README.md y aplique la solución apropiada.

Ejecute los siguientes comandos para ver el contenido de los códigos fuente contenidos en el directorio `src`:
    
    $ tree -fl src

    o

    $ cmd //c "tree /f /a src"    (para usuarios Windows)

Los códigos son compilados en el directorio `mods` con el siguiente comando:

    $ ./compile.sh

Ahora crea el paquete (jar files) con el siguiente comando:

    $ ./packaging.sh

Ejecute los siguientes comandos para ver los paquetes (jar files) creados por el script anterior:

    $ tree -fl mlib

    o 

    $ cmd //c "tree /f /a mlib"    (para usuarios Windows)
    
Usted verá que el módulo `org.astro` es empaquetado para indicar que su version es la `1.0`. El módulo `com.greetings` ha sido empaquetado para indicar que su main class es `com.greetings.Main`. El módulo `com.greetings` puede ser ejecutado sin necesidad de especificar cual es su main class (`Main.class`).
    
Y el ejemplo puede ser ejecutado con el siguiente comando:
    
    $ ./run.sh
    
Revise el contenido the los scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

Vea [../../01_Greetings/es/README.md](../../01_Greetings/es/README.md) para aprender mas acerca de la convención de nombres de paquete y módulos y como evitar confusiones.
