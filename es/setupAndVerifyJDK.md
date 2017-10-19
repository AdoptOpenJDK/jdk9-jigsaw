## Descargue, instale y verifique JDK

#### Descargue e instale la útima versión JDK 9 EA

- Descargue JDK 9 EA
  
  - Early Access build de JDK9 esta disponible en [http://jdk.java.net/9/](http://jdk.java.net/9/)
  - ~~Early Access build of JDK9 with Project Jigsaw is available at [http://jdk.java.net/jigsaw/](http://jdk.java.net/jigsaw/)~~ (no se encuentra mantenida de manera separada nucca más)

- Instale JDK 9 EA
  
  - Instale utilizando un script

    **Usuarios Linux y MacOSX unicamente:** el bash script ```getJDK9.sh``` en el directorio raíz de este repositorio, ayuda con la descarga de la última versión Jigsaw JDK desde Oracle. Por favor ejecute ese script una vez el repositorio sea clonado. 

    **Usuarios Windows:** por favor sientese libre de ejecutar ese script si hay algunos inconvenientes ejecutando ese script , por favor haganos saber por medio de un pull request.

  - Instalacion manual

    - Linux
       - `tar -xvf jigsaw-jdk-9*.tar`
       - `mv -f jdk-9 [destination]`  (puede ser necesario usar `sudo` )
       - actualice su `.bashrc` con `export JAVA_HOME=[jdk destination]`
       - actualice su `.bashrc` con `export PATH=$JAVA_HOME/bin:$PATH`
       - `source ~/.bashrc` 
    
         `[destination]` usualmente `/usr/lib/...` o un directorio relacionado
    
    - MacOSX 
       #### Proceso manual
       - Por favor refierase a [esta guia]( https://github.com/musinoli/guides/blob/master/setup_jigsaw.md) - gracias a Nolita ([@musinoli](https://github.com/musinoli)) por su ayuda
       - En caso de que un archivo `.dmg` este disponible, descárguelo y sigua los pasos de instalación, puede ser necesario actualizar su `JAVA_HOME` e iniciar los scripts como esta mencionado en [esta guía]( https://github.com/musinoli/guides/blob/master/setup_jigsaw.md).
       - actualice su `.bashrc` con `export PATH=$JAVA_HOME/bin:$PATH`

       #### Vía Brew y jenv
       Necesitará la última version de brew con cask support (1.1.13 al momento de escribir este documento). Si no tiene brew instalado, consulta [aqui](https://brew.sh/) primero.
       - `brew install jenv`
       - `brew install caskroom/versions/java9-beta`

       Necesita asegurarse que jenv esta disponible en cada sesion de terminal. Adicione `eval "$(jenv init -)"` en su archivo shell's .rc 

       Ahora ejecute `jenv versions`. La salida puede diferir (si tiene otros JDK's instalados) pero en general usted deberia ver algo como:
       ```
       ➜  ~ jenv versions
         system
       * 1.8 (set by /Users/ioannis/.jenv/version)
         1.8.0.121
         9-ea
         oracle64-1.8.0.121
         oracle64-9-ea
       ```
       Si `9-ea` o `oracle64-9-ea` no aparece, adicionelo manualmente

       `jenv add /Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home`

       Si usted quiere limitar el uso de JDK 9 para códigos en un directorio específico, vaya hasta ese directorio y ejecute:

       `jenv local 9-ea`

       e.g.
       ```
       ➜  ~ cd test
       ➜  test: jenv local 9-ea
       ➜  test: jenv version
       9-ea (set by /Users/ioannis/test/.java-version)
       ➜  test: java -version
       java version "9-ea"
       Java(TM) SE Runtime Environment (build 9-ea+163)
       Java HotSpot(TM) 64-Bit Server VM (build 9-ea+163, mixed mode)
       ```
        
    - Windows
       - el formato de archivo ha cambido a través del tiempo, puede que usted descargue un archivo `.exe` o `.zip`.
         - Si usted tiene un archivo ejecutable, ejecútelo y siga las instruciones
         - Si usted tiene un archivo zip, descomprímalo
       - Recuerde el directorio destino 
       - Edite las variables de entorno `JAVA_HOME` y `PATH` a traves de `My Computador` > `Propiedades` opción
         - `JAVA_HOME`: `JAVA_HOME=[jdk destination]`
         - `PATH`: `PATH=%JAVA_HOME%/bin;%PATH%`
         
           `[destination]` usualmente `C:\Program Files\Java\...` o un directorio relacionado  

##### Verifique la instalaccion de JDK

###### java

Luego de descargar e instalar JDK 9 EA (build 165) binary con los pasos anteriores, intente los siguientes comandos:

```
    $ java -version
```

Como salida usted debe obtener algo similar a:

```
    java version "9-ea"
    Java(TM) SE Runtime Environment (build 9-ea+165)
    Java HotSpot(TM) 64-Bit Server VM (build 9-ea+165, mixed mode)
```

###### javac

```
    $ javac -version
```

Como salida usted debe obtener algo similar a:

```
    javac 9-ea
```

###### jlink

Verifique si `jlink` esta disponible en su actual ambiente:

```
    $ jlink --version
```

Como salida usted debe obtener algo similar a:

```
    9
```

###### jshell

Verifique si `jshell` esta disponible en su actual ambiente:

```
    $ jshell --version
```

Como salida usted debe obtener algo similar a:

```
    jshell 9
```

###### jdeps

Verifique si `jdeps` esta disponible en su actual ambiente:

```
    $ jdeps --version
```

Como salida usted debe obtener algo similar a:

```
    9
```

###### jmod

Verifique si `jmod` esta disponible en su actual ambiente:

```
    $ jmod --version
```

Como salida usted debe obtener algo similar a:

```
    9
```


##### Alternativa para mantener su JAVA\_HOME y PATH

*NOTA*: Unicamente para usuarios Linux y MacOSX. 

Si usted no quiere cambiar ```JAVA_HOME``` and ```PATH``` y quiere tener una forma fácil de cambiar su version local de Java:

- Mantenga sus archivos descomprimidos en un directorio. Por ejemplo:

```
    $ pwd
    /usr/local/java
    
    $ ls -og
    total 12
    drwxr-xr-x 8 4096 sep 23  2016 jdk1.8.0_111
    drwxr-xr-x 8 4096 dic 13  2016 jdk1.8.0_121
    drwxr-xr-x 8 4096 ago 15 12:10 jdk-9+181 
```

- Cree un symbolic link ```jdk``` para acceder a JDK: 

```
    $ ln -s jdk-9+181 jdk 

    $ ls -og
    total 12
    lrwxrwxrwx 1   12 abr 17 17:16 jdk -> jdk-9+181
    drwxr-xr-x 8 4096 sep 23  2016 jdk1.8.0_111
    drwxr-xr-x 8 4096 dic 13  2016 jdk1.8.0_121
    drwxr-xr-x 8 4096 ago 15 12:10 jdk-9+181 
```

```JAVA_HOME``` y ```PATH``` usaran ```/usr/local/java/jdk``` por siempre.

Para cambiar su version local de Java, únicamente necesita cambiar el symbolic link.

Ahora regrese a [Descargue, instale y verifique JDK](./README.md#setup-all-platforms) en el archivo [README.md](./README.md) y continue con el resto de los pasos.
