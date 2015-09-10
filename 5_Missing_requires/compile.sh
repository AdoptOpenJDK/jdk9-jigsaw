COM_GREETINGS_FOLDER="mods/com.greetings"
ORG_ASTRO_FOLDER="mods/org.astro"

echo "Creating folders $ORG_ASTRO_FOLDER and $COM_GREETINGS_FOLDER, if they do not exists."
mkdir -p $ORG_ASTRO_FOLDER $COM_GREETINGS_FOLDER

echo "Compiling modules in $ORG_ASTRO_FOLDER"
javac -d $ORG_ASTRO_FOLDER \
        src/org.astro/module-info.java src/org.astro/org/astro/World.java
tree -fl $ORG_ASTRO_FOLDER

echo "Compiling modules in $COM_GREETINGS_FOLDER"
javac -modulepath mods -d $COM_GREETINGS_FOLDER \
        src/com.greetings/module-info.java src/com.greetings/com/greetings/Main.java

tree -fl $COM_GREETINGS_FOLDER