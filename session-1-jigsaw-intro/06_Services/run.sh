#!/bin/bash

set -eu

source ../../common-functions.sh

echo ""
echo "${info} *** Running 'com.greetings.Main' from within the mods folder (depends on 'com.socket' from within the mods folder) *** ${normal}"
echo ""
java --module-path mods \
     --module com.greetings/com.greetings.Main
echo ""
echo "${info} *** 'com.greetings.Main' imports and creates 'com.socket.NetworkSocket' *** ${normal}"
echo "${info} *** 'NetworkSocket.open()' returns a type of NetworkSocket with the help of a factory *** ${normal}"
echo "${info} *** The factory is a service provider that iterates through list of loaded services *** ${normal}"
