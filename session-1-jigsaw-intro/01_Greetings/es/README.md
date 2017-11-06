### Greetings ejemplo

Este primer ejemplo es un módulo (module) llamado `com.greetings` que simplemente imprime la palabra "Greetings!". 

El módulo del ejemplo consiste en dos archivos: 
La declaración del módulo (`module-info.java`) y la clase `Main`.

Convenciones Java para el nombre de módulos y paquetes: el directorio `src` contiene un módulo `com.greetings` (file-system: `com.greetings`) el cual para este caso es también el nombre del módulo.
Este módulo contiene un paquete (package) llamado `com.greetings` (file-system: `com/greetings`).

Ejecute los siguientes comandos para ver el contenido de cada clase Java contenida en el directorio `src`:

    $ cat src/com.greetings/module-info.java

    $ cat src/com.greetings/com/greetings/Main.java

**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la seccion [Download and install the `tree` and `wget` command](../../es/README.md) en el documento the README.md y aplique la solución apropiada.

El código es compilado en el directorio `mods/com.greetings` con los siguientes comandos:

    $ ./compile.sh
    
Y ejecutamos el ejemplo con el siguiente comando:
    
    $ ./run.sh
    
Revise el contenido the los dos scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

##### Alerta de confusión!

Por favor no se confunda con la convención de nombres de módulos y paquetes, a continuación la aclaración:

Módulos en Java siguen la siguiente convención, el nombre es literalmente el mismo del directorio:

| Nombre del Módulo   | Estructura en disco*  |
|---------------------|-----------------------|
| com.greetings.hello |  com.greetings.hello  |
| com.greetings       |  com.greetings        |
| com                 |  com                  |


Paquetes en Java siguen la usual convención:

| Nombre del paquete  | Estructura en disco*  |
|---------------------|-----------------------|
| com.greetings.hello | com/greetings/hello   |
| com.greetings       | com/greetings         |
| abc                 | abc                   |

* `Estructura en disco*`, significa la `estructura de directorio - archivo` creada como resultado del módulo o el paquete. Para el caso del Módulo com.greetings.hello existe en disco un único directorio llamado `com.greetings.hello`, para el caso del paquete com.greetings.hello existira en disco un directorio llamado `com` dentro de este un llamado `greetings` y dentro de este uno llamado `hello`
