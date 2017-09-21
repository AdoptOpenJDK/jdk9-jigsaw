## Download, install and configure Eclipse Oxygen for JDK9

### Prerequisite

Before installing Eclipse IDE please install JDK9

#### Download Eclipse Oxygen

The current version of the Eclipse IDE can be downloaded at [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/).
	
Depending on the OS a file **eclipse-inst-[mac/linux][64/32].tgz** (Linux/Mac OSX) or **eclipse-inst-[win][64/32].zip** will be downloaded.
	
Please unpack that file and you will get an installer for the current eclipse version.
	
#### Install	

Please Run the installer to get eclipse installed on your machine.
	
#### Configure Eclipse for running with JDK9(ea)

The Eclipse installer will automatically find the most current JDK version and configure the the Eclipse launcher to use it.

Though Eclipse is not able to use JDK9 out of the box because of missing dependencies.

To get Eclipse started, those missing dependencies (missing modules) have to be provided.

This is done by adding the line: 

**--add-modules=java.se.ee**

to the file **eclipse.ini** in the Eclipse installation.

##### Mac OSX

The file **eclipse.ini** is located in the installed **eclipse.app** package.

To change the content of the file browse to your installation folder

**e.g.: /Applications/jee-oxygen/** and perform a right click on eclipse.app.

In the popup menu choose **show package contents** and navigate to **./Contents/Eclipse**.

Open eclipse.ini with your preferred text editor and add the line:

**--add-modules=java.se.ee**

to the end of the file (last line in **-vmargs** block).

##### Linux

TBD

#### Windows

TBD

		


