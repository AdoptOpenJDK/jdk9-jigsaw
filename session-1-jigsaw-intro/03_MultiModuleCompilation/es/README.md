### Multi-module compilation ejemplo

En este ejemplo los módulos son compilados al mismo tiempo a diferencia del ejemplo anterior donde los módulos son compilados de manera secuencial.

**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la sección [Descargue e instale los comandos `tree` y `wget`](../../../es/README.md) en el documento README.md y aplique la solución apropiada.

Ejecute los siguientes comandos para ver los códigos contenidos en el directorio `src`:

    $ tree -fl src

    or 

    $ cmd //c "tree /f /a src"    (para usuarios Windows)

El código es compilado en los directorios `mods/org.astro` and `mods/com.greetings` con los siguientes comandos:

    $ ./multiModCompile.sh
    
Ejecute el siguiente comando para ver los módulos creados por medio del script anterior:

    $ find mods -type f
    
Y el ejemplo puede ser ejecutado con el siguiente comando:
    
    $ ./run.sh
    
Revise el contenido the los dos scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

Vea [../../01_Greetings/es/README.md](../../01_Greetings/es/README.md) para aprender mas acerca de la convención de nombres de paquete y módulos y como evitar confusiones.

**Ver el ejemplo:**
Para ver los códigos fuentes de este ejemplo vaya a [el directorio 03_MultiModuleCompilation](../), ahí encontrará la versión en Inglés del presente documento, el directorio `src` con los códigos fuentes, y algunos scripts que identificará con la extensión `.sh`.
