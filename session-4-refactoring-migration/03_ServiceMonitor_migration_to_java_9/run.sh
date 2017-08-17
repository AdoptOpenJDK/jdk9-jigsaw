#!/bin/bash

echo "--- Checking for Java 9 ---"
source ././../../common-functions.sh
echo "--- Java 9 found ---"
java -version

echo ""
echo "--- LAUNCH ---"

echo " > adding symbolic links to Java command(s) ---"

ln -f -s $(which java) java9

echo " > run monitor"
echo ""

# the classpath is needed for Spark's dependencies
java9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
	--add-modules monitor.observer.alpha,monitor.observer.beta \
	--add-modules monitor.utils,monitor.statistics \
	--add-modules monitor.rest \
	--add-modules java.xml.bind \
	monitor.Main
