# jdk9-jigsaw

+See [English version](../README.md) (Ver la [versión en Inglés](../README.md))

Ejemplos de algunas de las nuevas caracteristicas de Jigsaw liberadas en el la versión Early Access build de JDK9.

Algunos de los ejemplos son tomados del Projecto Jigsaw: Module System Quick-Start Guide, para más información visita [http://openjdk.java.net/projects/jigsaw/quick-start](http://openjdk.java.net/projects/jigsaw/quick-start) también tenemos contribuiciones de la comunidad Java.

___


####   Por favor  tenga presente que usted esta en el `exercise-solutions` branch el cual contiene la solución a los ejercicios y es principalmente para principiantes o si usted tiene un bloqueo tratando de solucionar los ejercicios del `master` branch. 

####   Ver directamente la solución lo aleja del reto de resolver los ejercicios. Cambiese al `master` branch en el caso de que quiera continuar y divertise solucionando los  ejercicios y trabajando en este reto.

___

## Configuracion (todas las plataformas)

Ver [Descargue e instale y verifique JDK](setupAndVerifyJDK.md) y regrese a esta página para continuar con el resto de pasos.
         
#### Descargue e instale `git` o `git-bash`

- Asegurese de tener un cliente `git` instalado en su máquina local machine/VM/vagrant box, para usuarios Windows `git-bash` es recomendado 

#### Descargue e instale los comandos `tree` y `wget`

Ver [Descargue e instale tree y wget](setupTreeAndWget.md) y regrese a esta página para continuar con el resto de los pasos.

#### Vagrant box

- Alternativamente Vagrant box es disponible en https://github.com/ali-ince/LJC_April2017_Hackday, por favor haga uso de esta utilidad (thanks @ali-ince).

#### (Opcional) Instale JDK 9 compliant IDE

- Instale la última version de su IDE preferido (IntelliJ, Eclipse, NetBeans - paid or community version) que soporte JDK 9 EA, una vez instalado configure el IDE para que utilice el JDK 9 EA instalado en los pasos anteriores.

Ver [Descargar, instalar y configurar Eclipse Oxygen para JDK9](../setupEclipseOxygen.md).

#### Otras preparaciones

- Familiaricese con la linea de comandos un poco debido a que será usado durante el fin de semana

- Prepare sus VMs or cloud instances con los pasos anteriores, en caso que su máquina local no este disponible para nuevas instalaciones o configuraciones 

**Nota:** los archivos bash proveidos pueden funcionar en Linux y en teoría también en MacOS.

##### Usuarios Windows

 - si usted usa `git-bash` (Recomendado) o `cgywin` podria funcionar para usted - ejecutar los scripts `.sh` en uno de los ambientes. En el peor de los escenarios, nosotros tendremos que convertir manualmente los archivos `.sh` en `.bat`, con algunos cambios estos podrian funcionar. Estaremos felices de recibir pull request para esto. O usted puede usar **bach**, una plataforma independiente de Java Shell Builder. Su uso es explicado en `session-3-jshell`.
 - revisa si el path de JDK (o `%JAVA_HOME%`) ha sido adicionado al la variable de entorno `PATH` en caso contrario adicionala de manera manual
 - asegurese que el JDK esta instalado en una carpeta donde el nombre no contenga espacios en blanco
 - en caso que esta carpeta luzca similar a `C:\Program files...`, asegurese que (el nombre no debe contener espacios):
    - el path del JDK en `JAVA_HOME` es `C:\Program~1` en vez de `C:\Program files...`
    - la variable de entorno `PATH` refiere al la variable de entorno `JAVA_HOME`
    - la variable de entorno`CLASSPATH` ref a la variable de entorno `JAVA_HOME`
 - en algunas instancias `jlink` no estaba disponible aún cuando el JDK es instalado via archivo `.exe`, incluso después de realizado los pasos anteriores de configuracion (por favor verifica antes de continuar)

##### Usuarios MacOSX
 - `jlink` no esta aun disponible cuando el JDK es instalado via archivo`.dmg` file (por favor verifica antes de continuar)

## Ejercicios / ejemplos cubiertos

Por favor asegurese de haber verificado que lo el JDK funcionen en su ambiente con la ayuda del documento [Descargue e instale y verifique JDK](setupAndVerifyJDK.md). 

- Sesión 1: Jigsaw Introduction
   - Greetings [./session-1-jigsaw-intro/01_Greetings](.././session-1-jigsaw-intro/01_Greetings)
   - Greetings world [./session-1-jigsaw-intro/02_GreetingsWorld](.././session-1-jigsaw-intro/02_GreetingsWorld)
   - Multi-module compilation [./session-1-jigsaw-intro/03_MultiModuleCompilation](.././session-1-jigsaw-intro/03_MultiModuleCompilation)
   - Packaging [./session-1-jigsaw-intro/04_Packaging](.././session-1-jigsaw-intro/04_Packaging)
   - Missing requires [./session-1-jigsaw-intro/05_Missing_requires](.././session-1-jigsaw-intro/05_Missing_requires)
   - Missing exports [./session-1-jigsaw-intro/05_Missing_exports](.././session-1-jigsaw-intro/05_Missing_exports)
   - Services [./session-1-jigsaw-intro/06_Services](.././session-1-jigsaw-intro/06_Services)
   - javac --patch-module option [./session-1-jigsaw-intro/07_patch_module_option](../session-1-jigsaw-intro/07_patch_module_option)
   - Modules export conflict [./session-1-jigsaw-intro/08_ModulesExportConflict](../session-1-jigsaw-intro/08_ModulesExportConflict)
   - Automatic modules [./session-1-jigsaw-intro/09_Automodules](../session-1-jigsaw-intro/09_Automodules)

- Sesión 2: JLink
   - JLink example [./session-2-jlink/01_JLink](../session-2-jlink/01_JLink)
   - JMod example [./session-2-jlink/02_JMod](../session-2-jlink/02_JMod) 

- Sesión 3: JShell
   - JShell quick tutorial [./session-3-jshell/](.././session-3-jshell/)
   - JShell examples [./session-3-jshell/JShell-Examples](.././session-3-jshell/JShell-Examples)
   - shellFX [./session-3-jshell/shellFX/](.././session-3-jshell/shellFX/)
   - teamshell [./session-3-jshell/teamshell/](.././session-3-jshell/teamshell/)
   - bach - Java Shell Builder [./session-3-jshell/bach-building-with-jshell](.././session-3-jshell/bach-building-with-jshell)
   
- Sesión 3: Refactoring/migration sessions
   - Junit 5 migration to Java 9 modules [./session-3-refactoring-migration/01_junit5_to_java_9](../session-3-refactoring-migration/01_junit5_to_java_9)
   - Building Java 9 Modules using Gradle (from monolith to modular) [./session-3-refactoring-migration/02_monolith_to_modular_using_gradle](../session-3-refactoring-migration/02_monolith_to_modular_using_gradle)
   - ServiceMonitor - Migration from Java 8 to Java 9 [./session-3-refactoring-migration/03_ServiceMonitor_migration_to_java_9](../session-3-refactoring-migration/03_ServiceMonitor_migration_to_java_9)
   - ServiceMonitor - Modularisation to Java 9 Modules (Maven project) [./session-3-refactoring-migration/04_ServiceMonitor_modularisation_to_java_9](../session-3-refactoring-migration/04_ServiceMonitor_modularisation_to_java_9)
        
Cada ejemplo esta empaquetado en un directorio el cual contiene sus propios scripts bash para compilar, empaquetar y ejecutar los respectivos ejemplos. Use el respectivo scripts para cada uno de los ejemplos.

## Contribuciones de la Comunidad

Vea [Guías de como contribuir](../CONTRIBUTING.md).

## Licencia

Vea [Licencia](../LICENSE) documento para leer los términos y condiciones de uso.

## Recursos

### Lecturas recomendadas
- [The State of the Module System](http://openjdk.java.net/projects/jigsaw/spec/sotms/)
- [JEP 261](http://openjdk.java.net/jeps/261)
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html) <br/>
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html)

### Otros recursos
- [Module System JSR (376)](https://www.jcp.org/en/jsr/detail?id=376)
- [JDK 9 / Jigsaw Resources](./Java-9-Resources.md)
- [Adopt OpenJDK homepage](https://adoptopenjdk.java.net/)
- [Adopt OpenJDK: Getting Started Kit](http://bit.ly/1NUkPWw)

---

![Yo te necesito para el desarrollo de Java SE 9](../I-need-you-for-Java-SE-9-development.png "Yo te necesito para el desarrollo de Java SE 9")
