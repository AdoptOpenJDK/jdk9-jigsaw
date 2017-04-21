# jdk9-jigsaw

Examples of some of the features of Jigsaw released in the Early Access build of JDK9.

The examples here are directly from the Project Jigsaw: Module System Quick-Start Guide,
see [http://openjdk.java.net/projects/jigsaw/quick-start](http://openjdk.java.net/projects/jigsaw/quick-start).

## Examples covered

- Session 1: Jigsaw Introduction
   - Greetings [./session-1-jigsaw-intro/01_Greetings](./session-1-jigsaw-intro/01_Greetings)
   - Greetings world [./session-1-jigsaw-intro/02_GreetingsWorld](./session-1-jigsaw-intro/02_GreetingsWorld)
   - Multi-module compilation [./session-1-jigsaw-intro/03_MultiModuleCompilation](./session-1-jigsaw-intro/03_MultiModuleCompilation)
   - Packaging [./session-1-jigsaw-intro/04_Packaging](./session-1-jigsaw-intro/04_Packaging)
   - Missing requires [./session-1-jigsaw-intro/05_Missing_requires](./session-1-jigsaw-intro/05_Missing_requires)
   - Missing exports [./session-1-jigsaw-intro/05_Missing_exports](./session-1-jigsaw-intro/05_Missing_exports)
   - Services [./session-1-jigsaw-intro/06_Services](./session-1-jigsaw-intro/06_Services)
   - javac --patch-module option [./session-1-jigsaw-intro/08_XModules_Overries](session-1-jigsaw-intro/08_patch_module_option)
   - Modules export conflict [./session-1-jigsaw-intro/10_ModulesExportConflict](./session-1-jigsaw-intro/10_ModulesExportConflict)

- Session 2: JLink
   - The linker [./session-2-jlink/07_The_linker](./session-2-jlink/07_The_linker) 
   - JLink example [./session-2-jlink/09_JLink](./session-2-jlink/09_JLink)

- Session 3: JShell [./session-3-jshell/](./session-3-jshell/)   
        
Each example is enclosed in a folder of its own containing bash scripts to compile, package and run the respective examples. Use these scripts for each of the examples.

**Note:** the bash files provided should work on Linux and in theory on the MacOS as well.
For Windows if you use `git-bash` (recommended) or `cgywin` they should work for you. In the worst case scenario, we would have to manually convert the `.sh` files into `.bat`, with minor tweaks
should also work there. Happy to received a pull request for it.

## Setup (all platforms)

#### Download latest version of the JDK 9 EA

Early Access build of Jigsaw JDK9 is available at [https://jdk9.java.net/jigsaw/](https://jdk9.java.net/jigsaw/).

**Linux and MacOSX users only:** the bash script ```getJigsawJDK.sh``` in the root directory of this repo, helps download the latest Jigsaw JDK from Oracle. Please run this once the repo is cloned.

##### Verify JDK installation

Say you have downloaded and installed the JDK 9 EA (build 165) binary from the above step, try the below command:

```
    $ java -version
```

As output you should get something like this:
```
    java version "9-ea"
    Java(TM) SE Runtime Environment (build 9-ea+165)
    Java HotSpot(TM) 64-Bit Server VM (build 9-ea+165, mixed mode)
```

#### Download and install `git` or `git-bash`

- Ensure you have a `git` client installed on your local machine/VM/vagrant box, for Windows users `git-bash` is recommended 

#### Download and install the `tree` and `wget` command

Please install the ```tree``` command before moving forward:

   - Linux
        ```
        sudo apt-get install tree
        sudo apt-get install wget
        ```

   - MacOSX
        ```
        install tree - http://apple.stackexchange.com/questions/128876/whats-the-equivalent-of-the-unix-tree-command-on-osx
        install wget - http://stackoverflow.com/questions/4572153/os-x-equivalent-of-linuxs-wget
        ```
        
   - Windows
        ```
        install `git-bash` (recommended) or `cgywin`
        ```
        
        The `tree` command is built into Windows. From within `git-bash` or `cygwin`.
        In case a script is invoking the `tree` command and failing, please replace the `tree -fl` keywords with `cmd //c "tree /f /a"`.
        
        Or manually convert the `.sh` files into `.bat`, with minor tweaks should also work there.

#### Vagrant box

- Alternatively a Vagrant box is available at https://github.com/ali-ince/LJC_April2017_Hackday, please make use of this facility (thanks @ali-ince).

#### (Optional) Install JDK 9 compliant IDE

- Install the latest IDE (IntelliJ, Eclipse, Netbeans - paid or community version) that supports JDK 9 EA, once installed configure the IDE to pickup the JDK 9 EA installed in the previous steps.

#### Other preparations

- Get familiar with the command-line a bit as we will be using much of it during the weekend

- Prepare your VMs or cloud instances with the above, in case your local machine is not up for any installation or configurations 

## Must reads
- [The State of the Module System](http://openjdk.java.net/projects/jigsaw/spec/sotms/)
- [JEP 261](http://openjdk.java.net/jeps/261)
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html) <br/>
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html)

## Other resources
- [Adopt OpenJDK homepage](https://adoptopenjdk.java.net/)
- [Adopt OpenJDK: Getting Started Kit](http://bit.ly/1NUkPWw)
- [JDK 9 / Jigsaw Resources](./Java-9-Resources.md)
