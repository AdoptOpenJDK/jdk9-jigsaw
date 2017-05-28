# jdk9-jigsaw

Examples of some of the features of Jigsaw released in the Early Access build of JDK9.

Many of the examples here are directly from the Project Jigsaw: Module System Quick-Start Guide, see [http://openjdk.java.net/projects/jigsaw/quick-start](http://openjdk.java.net/projects/jigsaw/quick-start) but we have also contributions from the Java community.

## Setup (all platforms)

#### Download and install latest version of the JDK 9 EA

- Download JDK 9 EA
  
  - Early Access build of JDK9 is available at [http://jdk.java.net/9/](http://jdk.java.net/9/)
  - Early Access build of JDK9 with Project Jigsaw is available at [http://jdk.java.net/jigsaw/](http://jdk.java.net/jigsaw/)

- Install JDK 9 EA
  
  - Install using a script

    **Linux and MacOSX users only:** the bash script ```getJigsawJDK.sh``` in the root directory of this repo, helps download the latest Jigsaw JDK from Oracle. Please run this once the repo is cloned. 

    **Windows users:** please feel free to run this script and if there are any tweaks to get it to work successfully, please let us know via a pull request.

  - Install manually

    - Linux
       - `tar -xvf jigsaw-jdk-9*.tar`
       - `mv -f jdk-9 [destination]`  (might need to use `sudo` here)
       - update your `.bashrc` with `export JAVA_HOME=[jdk destination]`
       - update your `.bashrc` with `export PATH=$JAVA_HOME/bin:$PATH`
       - `source ~/.bashrc` 
    
         `[destination]` usually `/usr/lib/...` or related folder
    
    - MacOSX 
       #### Manual way
       - Please refer to [this guide]( https://github.com/musinoli/guides/blob/master/setup_jigsaw.md) - thanks Nolita ([@musinoli](https://github.com/musinoli)) for your help
       - In case a `.dmg` file is available, download it and follow the installation steps, you will still have to update your `JAVA_HOME` and start script as mentioned in [this guide]( https://github.com/musinoli/guides/blob/master/setup_jigsaw.md).
       - update your `.bashrc` with `export PATH=$JAVA_HOME/bin:$PATH`

       #### Via Brew and jenv
       You will need the latest version of brew with cask support (1.1.13 at the time of writing). If you don't have brew installed, go [here](https://brew.sh/) first.
       - `brew install jenv`
       - `brew cask install java9-beta`

       You will need to make sure jenv is evaluated in every terminal session. Add `eval "$(jenv init -)"` to your shell's .rc file

       Now run `jenv versions`. The output might differ (if you have other JDK's installed) but in general you should see something like this:
       ```
       ➜  ~ jenv versions
         system
       * 1.8 (set by /Users/ioannis/.jenv/version)
         1.8.0.121
         9-ea
         oracle64-1.8.0.121
         oracle64-9-ea
       ```
       If `9-ea` or `oracle64-9-ea` does not appear, add it manually

       `jenv add /Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home`

       If you want to limit use of JDK 9 to source in a specific directory, switch to that directory and do:

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
       - the file format has changed over time, so you might have downloaded either an `.exe` or a `.zip`.
         - If you have an executable file, then run it and follow the instructions
         - If you have a zip file, then just unzip it
       - Make a note of the destination 
       - Edit the `JAVA_HOME` and `PATH` environment variables via the `My Computer` > `Properties` option
         - `JAVA_HOME`: `JAVA_HOME=[jdk destination]`
         - `PATH`: `PATH=%JAVA_HOME%/bin;%PATH%`
         
           `[destination]` usually `C:\Program Files\Java\...` or related folder  

##### Verify JDK installation

Say you have downloaded and installed the JDK 9 EA (build 165) binary from the above step, try the below commands:

```
    $ java -version
```

As output you should get something like this:
```
    java version "9-ea"
    Java(TM) SE Runtime Environment (build 9-ea+165)
    Java HotSpot(TM) 64-Bit Server VM (build 9-ea+165, mixed mode)
```

And

```
    $ javac -version
```

As output you should get something like this:
```
    javac 9-ea
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
        In case a script is invoking the `tree` command and failing, please replace the `tree -fl` keywords with `cmd //c "tree /f /a"`. If that does not work either use `ls -R` instead, except you won't see the output in a tree format.
        
        Or manually convert the `.sh` files into `.bat`, with minor tweaks should also work there.

        Thanks Richard Kolb ([@rjdkolb](http://github.com/rjdkolb)) for your continued support in this area.

#### Vagrant box

- Alternatively a Vagrant box is available at https://github.com/ali-ince/LJC_April2017_Hackday, please make use of this facility (thanks @ali-ince).

#### (Optional) Install JDK 9 compliant IDE

- Install the latest IDE (IntelliJ, Eclipse, NetBeans - paid or community version) that supports JDK 9 EA, once installed configure the IDE to pickup the JDK 9 EA installed in the previous steps.

#### Other preparations

- Get familiar with the command-line a bit as we will be using much of it during the weekend

- Prepare your VMs or cloud instances with the above, in case your local machine is not up for any installation or configurations 

**Note:** the bash files provided should work on Linux and in theory on the MacOS as well.

For Windows users, if you use `git-bash` (recommended) or `cgywin` they should work for you. 

In the worst case scenario, we would have to manually convert the `.sh` files into `.bat`, with minor tweaks should also work there. Happy to received a pull request for it.

## Examples covered

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

- Session 2: JLink
   - JLink example [./session-2-jlink/01_JLink](session-2-jlink/01_JLink)
   - JMod example [./session-2-jlink/02_JMod](session-2-jlink/02_JMod) 

- Session 3: JShell
   - JShell quick tutorial [./session-3-jshell/](./session-3-jshell/)
   - JShell examples [./session-3-jshell/JShell-Examples](./session-3-jshell/JShell-Examples)
   - shellFX [./session-3-jshell/shellFX/](./session-3-jshell/shellFX/)
   - teamshell [./session-3-jshell/teamshell/](./session-3-jshell/teamshell/)
        
Each example is enclosed in a folder of its own containing bash scripts to compile, package and run the respective examples. Use these scripts for each of the examples.

## Must reads
- [The State of the Module System](http://openjdk.java.net/projects/jigsaw/spec/sotms/)
- [JEP 261](http://openjdk.java.net/jeps/261)
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001053.html) <br/>
- [http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html](http://mail.openjdk.java.net/pipermail/adoption-discuss/2015-September/001056.html)

## Other resources
- [JDK 9 / Jigsaw Resources](./Java-9-Resources.md)
- [Adopt OpenJDK homepage](https://adoptopenjdk.java.net/)
- [Adopt OpenJDK: Getting Started Kit](http://bit.ly/1NUkPWw)
