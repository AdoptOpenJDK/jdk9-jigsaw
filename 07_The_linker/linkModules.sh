#!/bin/bash

set -eu

echo "Removing any existing greetings.jmod"
rm -f greetings.jmod

echo
echo "Creating a module (greetings.jmod) from multiple modules / packages / classes with jlink."
jmod create --class-path mods/com.greetings:mods/org.astro greetings.jmod

echo
echo "Enlisting the contents of the module (greetings.jmod)."
jmod list greetings.jmod
