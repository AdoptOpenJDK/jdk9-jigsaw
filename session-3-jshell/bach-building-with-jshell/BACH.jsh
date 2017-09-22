/*
 * Source (parse and compile) a local copy of Bach.java into this jshell session.
 */
/open bach/Bach.java

/*
 * Define convenient short names for the foundation JDK tools and commands.
 */
void run(String tool, Object... args) { JdkTool.run(tool, args); }
void java(Object... args) { run("java", args); }
void javac(Object... args) { run("javac", args); }
void javadoc(Object... args) { run("javadoc", args); }
void jar(Object... args) { run("jar", args); }
void jlink(Object... args) { run("jlink", args); }
void jmod(Object... args) { run("jmod", args); }
void jdeps(Object... args) { run("jdeps", args); }
void jdeprscan(Object... args) { run("jdeprscan", args); }
void javah(Object... args) { run("javah", args); }
void javap(Object... args) { run("javap", args); }
