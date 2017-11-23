### Missing exports ejemplo

En este ejemplo por error omitimos el `exports` en la declaración del módulo `com.astro` (en `module-info.java`).

    $ cat src/org.astro/module-info.java
    
**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la sección [Descargue e instale los comandos `tree` y `wget`](../../../es/README.md) en el documento README.md y aplique la solución apropiada.
    
Intente compilar los módulos utilizando el comando a continuación:

    $ ./compile.sh
    
La compilación falla con el siguiente error:

```
    src/com.greetings/com/greetings/Main.java:3: error: package org.astro is not visible
    import org.astro.World;
              ^
      (package org.astro is declared in module org.astro, which does not export it)
    1 error
```

Revise el contenido the los scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

Vea [../../01_Greetings/es/README.md](../../01_Greetings/es/README.md) para aprender mas acerca de la convención de nombres de paquete y módulos y como evitar confusiones.

**Ver el ejemplo:**
Para ver los códigos fuentes de este ejemplo vaya a [el directorio 05_Missing_exports](../), ahí encontrará la versión en Inglés del presente documento, el directorio `src` con los códigos fuentes, y algunos scripts que identificará con la extensión `.sh`
