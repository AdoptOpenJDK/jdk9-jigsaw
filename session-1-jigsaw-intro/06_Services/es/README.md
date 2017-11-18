### Services ejemplo

Servicios permiten bajo acoplamiento entre módulos de servicios consumidores y módulos de servicios proveedores

Este ejemplo tiene un módulo de servicio consumidor y un módulo de servicio proveedor:

- módulo `com.socket` exporta un API para acceso a sockets de red (network sockets). 
  El API esta en el paquete `com.socket` por esto este paquete es exportado. 
  El API es plug-able para permitir implementaciones alternativas. 
  La clase del tipo de servicio esta `com.socket.spi.NetworkSocketProvider` en el mismo módulo y por esto el paquete `com.socket.spi` también es exportado.

- módulo `org.fastsocket` es un módulo de servicio proveedor. 
  Este provee una implementación de `com.socket.spi.NetworkSocketProvider`. 
  No exporta paquetes. 
  
Ejecute los siguientes comandos para ver el contenido de los códigos fuente contenidos en el directorio `src`:

- módulo `com.socket`
    
    ```
    $ cat src/com.socket/module-info.java
    $ cat src/com.socket/com/socket/NetworkSocket.java
    $ cat src/com.socket/com/socket/spi/NetworkSocketProvider.java
    ```
    
- módulo `org.fastsocket`

    ```
    $ cat src/org.fastsocket/module-info.java
    $ cat src/org.fastsocket/org/fastsocket/FastNetworkSocketProvider.java
    $ cat src/org.fastsocket/org/fastsocket/FastNetworkSocket.java
    ```
    
- módulo `com.greetings`
    ```
    $ cat src/com.greetings/module-info.java
    $ cat src/com.greetings/com/greetings/Main.java
    ```
     
**Nota:** en caso de que alguno de los anteriores scripts `.sh` falle debido al comando `tree`, por favor vea la sección [Descargue e instale los comandos `tree` y `wget`](../../../es/README.md) en el documento README.md y aplique la solución apropiada.

Ambos módulos son compilados juntos en el directorio `mods` con el siguiente comando:

    $ ./compile.sh
    
Y ejecute los ejemplos con el siguiente comando:
    
    $ ./run.sh
    
Revise el contenido the los scripts (use el comando `cat` o un editor de texto) para ver que hacen y porque - instrucciones interesantes e información encontrará en cada uno.

Vea [../../01_Greetings/es/README.md](../../01_Greetings/es/README.md) para aprender mas acerca de la convención de nombres de paquete y módulos y como evitar confusiones.
