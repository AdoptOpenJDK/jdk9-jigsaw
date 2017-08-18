# ServiceMonitor - Modularisation

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
* to contact REST endpoints:
	* `curl http://localhost:4567/stats/json`
	* `curl http://localhost:4567/stats/json64 | base64 -d`
	* `curl http://localhost:4567/stats/xml`
	
* scripts have also been provided (try them out in the end when it works):
    * compile.sh
    * run.sh

## Troubles

### Modularization

* unexpressed transitive dependencies:
	* _monitor_ ~> _monitor.statistics_ (for `Statistics` et al)
	* _monitor.rest_ ~> _jackson.core_ (for `JsonProcessingException`)
	
## Cheatsheet

To help and give a bit more hints during the refactoring process refer to our [modularisation cheatsheet](./modularisation-cheatsheet.md).

Warning: but this might just take away all the fun learning.
