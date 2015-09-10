PATCHES_FOLDER="mypatches/java.base"
SRC_FOLDER="src"

echo "Compiling a module by excluding it first."

javac -Xmodule:java.base -d $PATCHES_FOLDER \
        src/java.base/java/util/concurrent/ConcurrentHashMap.java

tree -fl $PATCHES_FOLDER
tree -fl $SRC_FOLDER