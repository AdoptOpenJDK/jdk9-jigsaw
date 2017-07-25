#!/bin/bash

echo "--- COMPILATION & PACKAGING ---"

echo " > creating clean directories"
rm -rf build
mkdir build
mkdir build/classes
mkdir build/jars
mkdir build/class-path
mkdir build/module-path

cp deps/* build/class-path


echo " > creating monitor.utils"
javac9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
	-d build/classes/monitor.utils \
	$(find monitor.utils/src/main/java -name '*.java')
jar9 --create \
	--file build/jars/monitor.utils.jar \
	-C build/classes/monitor.utils .
cp build/jars/monitor.utils.jar build/class-path


echo " > creating monitor.observer"
javac9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
	-d build/classes/monitor.observer \
	$(find monitor.observer/src/main/java -name '*.java')
jar9 --create \
	--file build/jars/monitor.observer.jar \
	-C build/classes/monitor.observer .
cp build/jars/monitor.observer.jar build/class-path


echo " > creating monitor.observer.alpha"
javac9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
	-d build/classes/monitor.observer.alpha \
	$(find monitor.observer.alpha/src/main/java -name '*.java')
jar9 --create \
	--file build/jars/monitor.observer.alpha.jar \
	-C build/classes/monitor.observer.alpha .
cp build/jars/monitor.observer.alpha.jar build/class-path


echo " > creating monitor.observer.beta"
javac9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
	-d build/classes/monitor.observer.beta \
	$(find monitor.observer.beta/src/main/java -name '*.java')
jar9 --create \
	--file build/jars/monitor.observer.beta.jar \
	-C build/classes/monitor.observer.beta .
cp build/jars/monitor.observer.beta.jar build/class-path


echo " > creating monitor.statistics"
javac9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
	-d build/classes/monitor.statistics \
	$(find monitor.statistics/src/main/java -name '*.java')
jar9 --create \
	--file build/jars/monitor.statistics.jar \
	-C build/classes/monitor.statistics .
cp build/jars/monitor.statistics.jar build/class-path


echo " > creating monitor.persistence"
javac9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
	-d build/classes/monitor.persistence \
	$(find monitor.persistence/src/main/java -name '*.java')
jar9 --create \
	--file build/jars/monitor.persistence.jar \
	-C build/classes/monitor.persistence .
cp build/jars/monitor.persistence.jar build/class-path


echo " > creating monitor.rest"
javac9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
 	--add-modules java.xml.bind,java.xml.ws.annotation \
	--patch-module java.xml.ws.annotation=build/class-path/jsr305-3.0.2.jar \
	-d build/classes/monitor.rest \
	$(find monitor.rest/src/main/java -name '*.java')
jar9 --create \
	--file build/jars/monitor.rest.jar \
	-C build/classes/monitor.rest .
cp build/jars/monitor.rest.jar build/class-path


echo " > creating monitor"
javac9 \
	--class-path 'build/class-path/*' \
	--module-path build/module-path \
	-d build/classes/monitor \
	$(find monitor/src/main/java -name '*.java')
jar9 --create \
	--file build/jars/monitor.jar \
	-C build/classes/monitor .
cp build/jars/monitor.jar build/class-path
