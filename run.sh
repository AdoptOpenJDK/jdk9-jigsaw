#!/bin/bash

echo ""
echo "--- LAUNCH ---"

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
