### Ejemplo Greetings world

El segundo ejemplo actualiza la declaracion del módulo para declarar una dependencia con el módulo `org.astro`. El módulo `org.astro` exporta el API del paquete `org.astro`. 

**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la sección [Descargue e instale los comandos `tree` y `wget`](../../../es/README.md) en el documento README.md y aplique la solución apropiada.

Ejecute los siguientes comandos para ver el contenido de los códigos fuente contenidos en el directorio `src`:
    
    $ tree -fl src

    o

    $ cmd //c "tree /f /a src"    (para usuaerios Windows)

Ejecute los siguientes comandos para ver el contenido de cada clase Java contenida en el directorio `src`:

    $ cat src/org.astro/module-info.java

    $ cat src/org.astro/org/astro/World.java

El módulo `org.astro` exporta el paquete `org.astro` (vea `src/org.astro/module-info.java` para más detalles).

    $ cat src/com.greetings/module-info.java

    $ cat src/com.greetings/com/greetings/Main.java

El módulo `com.greetings` importa el paquete `org.astro` (vea `src/com.greetings/module-info.java` para más detalles).

Los códigos son compilados en el directorio `mods/org.astro` y `mods/com.greetings` con el siguiente comando:

    $ ./compile.sh
    
Y el ejemplo puede ser ejecutado con el siguiente comando:
    
    $ ./run.sh
    
Revise el contenido the los dos scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

Vea [../../01_Greetings/es/README.md](../../01_Greetings/es/README.md) para aprender mas acerca de la convención de nombres de paquete y módulos y como evitar confusiones.

**Ver el ejemplo:**
Para ver los códigos fuentes de este ejemplo vaya a [el directorio 02_GreetingsWorld ](../), ahí encontrará la version en Inglés del presente documento, el directorio `src` con los códigos fuentes, y algunos scritps que identificará con la extensión `.sh`
