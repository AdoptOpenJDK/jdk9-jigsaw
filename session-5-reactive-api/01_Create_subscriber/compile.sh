#!/bin/bash

set -eu

javac -d src/PrimeNumberSubscriber/target/classes \
      src/PrimeNumberSubscriber/com/project/*.java
	 