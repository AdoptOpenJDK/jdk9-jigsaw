### Services example

Services allow for loose coupling between service consumers modules and service providers modules.

This example has a service consumer module and a service provider module:

- module `com.socket` exports an API for network sockets. 
  The API is in package `com.socket` so this package is exported. 
  The API is plug-able to allow for alternative implementations. 
  The service type is class `com.socket.spi.NetworkSocketProvider` in the same module and thus package `com.socket.spi` is also exported.

- module `org.fastsocket` is a service provider module. 
  It provides an implementation of `com.socket.spi.NetworkSocketProvider`. 
  It does not export any packages. 
  
Perform the below commands to see the contents of the respective sources contained in the `src` folder:

- module `com.socket`
    
    ```
    $ cat src/com.socket/module-info.java
    $ cat src/com.socket/com/socket/NetworkSocket.java
    $ cat src/com.socket/com/socket/spi/NetworkSocketProvider.java
    ```
    
- module `org.fastsocket`

    ```
    $ cat src/org.fastsocket/module-info.java
    $ cat src/org.fastsocket/org/fastsocket/FastNetworkSocketProvider.java
    $ cat src/org.fastsocket/org/fastsocket/FastNetworkSocket.java
    ```
    
- module `com.greetings`
    ```
    $ cat src/com.greetings/module-info.java
    $ cat src/com.greetings/com/greetings/Main.java
    ```
     
**Note:** in case one of the below `.sh` script fails due to the `tree` command, please take a look at [Download and install the `tree` and `wget` command](../../README.md) section in the README.md file and apply the appropriate solution.

Both the modules are compiled together from the sources into the folder `mods` with the following commands:

    $ ./compile.sh
    
And we run the example with the following command:
    
    $ ./run.sh
    
Check the contents of both these script files (use the `cat` command or a text editor) to see what they are doing and why - interesting instructions and information in there.

See [../01_Greetings/README.md](../01_Greetings/README.md) to learn more about package and module naming conventions and how to avoid confusions between them.
