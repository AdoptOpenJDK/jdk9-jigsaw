#!/bin/bash

set -eu

javac -d src/Project_JDK8/target/classes \
      src/Project_JDK8/com/project/*.java
	 
javac -d src/Project_JDK9/target/classes \
      src/Project_JDK9/com/project/*.java
