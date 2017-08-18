# ServiceMonitor - Modularisation - Cheatsheet

Disclaimer: do not follow the steps below literally, in case of issues try to debug. If you think it is an issue, report back in the feedback form or ask on the IRC channel.

Enjoy the modularisation journey!

## Modularisation steps (bottom-up migration)

- Pre-modularisation detective work

        jdeps -s -R -cp 'monitor/target/libs/*' monitor/target/main-1.0-SNAPSHOT.jar

  `jdeps` tells us about the split package we encountered during the migration process 

        jdeps -s -R -include 'monitor.*' -cp 'monitor/target/libs/*' monitor/target/Machine-1.0-SNAPSHOT.jar

  unnamed modules have a 'not found' against them - these are our modules that are not Java 9 modules yet

        jdeps -s -R -include 'monitor.*' --dot-output . -cp 'monitor/target/libs/*' monitor/target/Machine-1.0-SNAPSHOT.jar

  creates a dot output of the module graph

  Use the `jdeps` output to navigate through your module graph and learn about the dependencies between the modules.

- Create modules
    - create `monitor.utils`
        - go to module in the IDE
            - In IntelliJ, Add the folder ... as a module via File > New > Module from Existing source (check search project recursively, and click on Next till you navigate till Finish, IntelliJ detects it to be a Java project)
        - Set the SDK and language level of the project and modules to Java 9
        - Go to `monitor.utils` > `src/main/java`, right mouse click > New > module-info.java, name the module monitor.utils
        - run `./compile.sh` from the project root
        - run `./run.sh` from the project root
        - put the jar onto the module-path in the `compile.sh` for monitor.utils
        - run `./compile.sh` from the project root
        - we get errors:
         
                monitor.rest/src/main/java/monitor/rest/MonitorServer.java:16: error: package monitor.utils is not visible
                import static monitor.utils.Utils.toBase64;
                                ^
                                
        - fix it by making the monitor.utils available in to monitor.rest during compile time (amend `compile.sh` for it, hint: --add-modules)
        - but also add it to monitor section in the compile.sh
        - run `./compile.sh` from the project root
        - issue fixed
        - run `./run.sh` from the project root
        - we get an error: 

                Exception in thread "main" java.lang.NoClassDefFoundError: monitor/utils/Utils
    
        - add the newly created module to the `run.sh`
    - now make monitor.observer modular
        - create the `module-info` and make the respective package(s) available
        - then make monitor.observer.alpha, monitor.observer.beta modular
        - create the `module-info` for both and make the respective package(s) available, also define the dependency (check source to find out)
        - make the respective changes to the `compile.sh` (find out the modular dependencies)
        - resolve the compiler failures
        - run `compile.sh` again
        - make the respective changes to the `run.sh` (use the modular dependencies)
        - resolve the runtime failures
        - run `run.sh` again
    - now make monitor.statistics modular
        - create the `module-info` and make the respective package(s) available, also define the dependency (check source to find out)
        - make the respective changes to the `compile.sh` (find out the modular dependencies)
        - resolve the compiler failures
        - run `compile.sh` again
        - make the respective changes to the `run.sh` (use the modular dependencies)
        - resolve the runtime failures
        - run `run.sh` again
    - now make `monitor.rest` modular
        - create the `module-info` and make the respective package(s) available, also define the dependency (check source to find out)
        - define the dependencies on the automatic (unnamed) module
        - make the respective changes to the `compile.sh` (find out the modular dependencies)
        - resolve the compiler failures
        - you will need to make sure the automatic (unnamed) modules from the class path to the module path (amend compile.sh)
        - run `compile.sh` again
        - make the respective changes to the `run.sh` (use the modular dependencies)
        - resolve the runtime failures
        - run `run.sh` again
        - run `curl http://localhost:4567/stats/xml`
        - this should fail: 
        
                javax.xml.bind.JAXBException: Package monitor.rest with JAXB class monitor.rest.StatisticsEntity defined in a module monitor.rest must be open to at least java.xml.bind module.
        
        - Amend the `module-info` to allow (opens) javax.xml.bind to reflectively see monitor.rest
        - run `compile.sh` again
        - run `run.sh` again
        - run `curl http://localhost:4567/stats/xml`
        - Continue modularising the remaining maven module(s)

If all of this does not make sense, watch the second half of Nicolai Parlog's video on [From Jar hell and Back](https://youtu.be/NKY2FYTCo7I).
