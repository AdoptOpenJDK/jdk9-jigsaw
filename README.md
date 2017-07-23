# ServiceMonitor - Migration

An example application for my book [_The Java 9 Module System_](https://www.manning.com/books/the-java-9-module-system?a_aid=nipa&a_bid=869915cb).
The _Service Monitor_ is an application that observes a hypothetical network of microservices by

* contacting individual services
* collecting and aggregating diagnostic data into statistics
* persisting statistics
* making statistics available via REST

It is split into a number of modules that focus on specific concerns.
Each module has its own directory that contains the known folder structure, e.g. `src/main/java`.

It was developed as a Java 8 application and now needs to be made compatible with Java 9 and then be modularized.

## Build and Execution

In the project's root folder:

* to build: `mvn clean install`
* to run: `java -cp 'monitor/target/libs/*':'monitor/target/main-1.0-SNAPSHOT.jar' monitor.Main`
* to contact REST endpoint: `curl http://localhost:4567/statistics`
