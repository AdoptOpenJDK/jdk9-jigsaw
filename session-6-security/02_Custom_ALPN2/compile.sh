#!/bin/bash

set -eu

javac -d src/CustomALPN/target/classes \
      src/CustomALPN/com/project/*.java
	 