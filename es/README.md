# jdk9-jigsaw

Ejemplos de algunas de las nuevas caracteristicas de Jigsaw liberadas en el la versión Early Access build de JDK9.

Algunos de los ejemplos son tomados del Projecto Jigsaw: Module System Quick-Start Guide, para más información visita [http://openjdk.java.net/projects/jigsaw/quick-start](http://openjdk.java.net/projects/jigsaw/quick-start) también tenemos contribuiciones de la comunidad Java.

___


####   Por favor  tenga presente que usted esta en el `master` branch el cual contiene ejercicios que necesitan ser resueltos consultando la documentación o los recursos proveidos en este repositorio. Pistas pueden ser proveidas dentro del código fuente o en los archivos de scripts. Si no encuentras la solución y quieres ver una posible solución puede cambiar al branch `exercise-solutions`. 

####   Ver directamente la solución lo aleja del reto de resolver los ejercicios. Mantengase en el `master` branch en el caso de que quiera continuar y divertise solucionando los  ejercicios y trabajando en este reto.

___

## Configuracion (todas las plataformas)

Ver [Download, install and verify JDK](setupAndVerifyJDK.md) y regrese a esta página para continuar con el resto de pasos.
         
#### Descarga e installa `git` o `git-bash`

- Ensure you have a `git` client installed on your local machine/VM/vagrant box, for Windows users `git-bash` is recommended 

#### Download and install the `tree` and `wget` command

See [Download and install tree and wget](setupTreeAndWget.md) and return to this page to continue with the rest of the steps.

#### Vagrant box

- Alternatively a Vagrant box is available at https://github.com/ali-ince/LJC_April2017_Hackday, please make use of this facility (thanks @ali-ince).

#### (Optional) Install JDK 9 compliant IDE

- Install the latest IDE (IntelliJ, Eclipse, NetBeans - paid or community version) that supports JDK 9 EA, once installed configure the IDE to pickup the JDK 9 EA installed in the previous steps.

See [Download, install and configure Eclipse Oxygen for JDK9](setupEclipseOxygen.md).

#### Other preparations

- Get familiar with the command-line a bit as we will be using much of it during the weekend

- Prepare your VMs or cloud instances with the above, in case your local machine is not up for any installation or configurations 

**Note:** the bash files provided should work on Linux and in theory on the MacOS as well.

##### Windows users

 - if you use `git-bash` (recommended) or `cgywin` should work for you - run the `.sh` scripts in one of the the environments. In the worst case scenario, we would have to manually convert the `.sh` files into `.bat`, with minor tweaks should also work there. Happy to receive a pull request for it. Or you could use **bach**, a platform independent Java Shell Builder. It's usage is explained in `session-3-jshell`.
 - check if the literal JDK path (or `%JAVA_HOME%`) has been added to the `PATH` environment variable or add it manually
 - ensure the JDK is installed in a folder where the name does not have a space in it 
 - in case folder name looks something `C:\Program files...`, ensure that (name does not contain spaces):
    - the path to the JDK in `JAVA_HOME` has `C:\Program~1` instead of `C:\Program files...`
    - the environment variable `PATH` refers to the `JAVA_HOME` environment variable
    - the environment variable `CLASSPATH` refers to the `JAVA_HOME` environment variable
 - in some instances `jlink` was not yet available when JDK is installed via the `.exe` file, even after the above path settings were applied (please verify beforehand)

##### MacOSX users
 - `jlink` is not yet available when JDK is installed via the `.dmg` file (please verify beforehand)

## Exercises / examples covered

Please ensure you have verified that the necessary JDK programs work in your environment with the help of the [Download, install and verify JDK](setupAndVerifyJDK.md) resource. 

- Session 1: Jigsaw Introduction
   - Greetings [./session-1-jigsaw-intro/01_Greetings](./session-1-jigsaw-intro/01_Greetings)
   - Greetings world [./session-1-jigsaw-intro/02_GreetingsWorld](./session-1-jigsaw-intro/02_GreetingsWorld)
   - Multi-module compilation [./session-1-jigsaw-intro/03_MultiModuleCompilation](./session-1-jigsaw-intro/03_MultiModuleCompilation)
   - Packaging [./session-1-jigsaw-intro/04_Packaging](./session-1-jigsaw-intro/04_Packaging)
   - Missing requires [./session-1-jigsaw-intro/05_Missing_requires](./session-1-jigsaw-intro/05_Missing_requires)
   - Missing exports [./session-1-jigsaw-intro/05_Missing_exports](./session-1-jigsaw-intro/05_Missing_exports)
   - Services [./session-1-jigsaw-intro/06_Services](./session-1-jigsaw-intro/06_Services)
   - javac --patch-module option [./session-1-jigsaw-intro/07_patch_module_option](session-1-jigsaw-intro/07_patch_module_option)
   - Modules export conflict [./session-1-jigsaw-intro/08_ModulesExportConflict](session-1-jigsaw-intro/08_ModulesExportConflict)
   - Automatic modules [./session-1-jigsaw-intro/09_Automodules](session-1-jigsaw-intro/09_Automodules)

- Session 2: JLink
   - JLink example [./session-2-jlink/01_JLink](session-2-jlink/01_JLink)
   - JMod example [./session-2-jlink/02_JMod](session-2-jlink/02_JMod) 

- Session 3: JShell
   - JShell quick tutorial [./session-3-jshell/](./session-3-jshell/)
   - JShell examples [./session-3-jshell/JShell-Examples](./session-3-jshell/JShell-Examples)
   - shellFX [./session-3-jshell/shellFX/](./session-3-jshell/shellFX/)
   - teamshell [./session-3-jshell/teamshell/](./session-3-jshell/teamshell/)
   - bach - Java Shell Builder [./session-3-jshell/bach-building-with-jshell](./session-3-jshell/bach-building-with-jshell)
   
- Session 3: Refactoring/migration sessions
   - Junit 5 migration to Java 9 modules [./session-3-refactoring-migration/01_junit5_to_java_9](session-3-refactoring-migration/01_junit5_to_java_9)
   - Building Java 9 Modules using Gradle (from monolith to modular) [./session-3-refactoring-migration/02_monolith_to_modular_using_gradle](session-3-refactoring-migration/02_monolith_to_modular_using_gradle)
   - ServiceMonitor - Migration from Java 8 to Java 9 [./session-3-refactoring-migration/03_ServiceMonitor_migration_to_java_9](session-3-refactoring-migration/03_ServiceMonitor_migration_to_java_9)
   - ServiceMonitor - Modularisation to Java 9 Modules (Maven project) [./session-3-refactoring-migration/04_ServiceMonitor_modularisation_to_java_9](session-3-refactoring-migration/04_ServiceMonitor_modularisation_to_java_9)
        
Each example is enclosed in a folder of its own containing bash scripts to compile, package and run the respective examples. Use these scripts for each of the examples.

## Community contributions

See [guidelines on how to contribute](CONTRIBUTING.md).

## License

See [License](LICENSE) document to find out about the licensing terms and conditions.

## Resources

### Must reads
- [The State of the Module System](http://openjdk.java.net/projects/jigsaw/spec/sotms/)
- [JEP 261](http://openjdk.java.net/jeps/261)
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html) <br/>
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html)

### Other resources
- [Module System JSR (376)](https://www.jcp.org/en/jsr/detail?id=376)
- [JDK 9 / Jigsaw Resources](./Java-9-Resources.md)
- [Adopt OpenJDK homepage](https://adoptopenjdk.java.net/)
- [Adopt OpenJDK: Getting Started Kit](http://bit.ly/1NUkPWw)

---

![I need you for Java SE 9 development](I-need-you-for-Java-SE-9-development.png "I need you for Java SE 9 development")
