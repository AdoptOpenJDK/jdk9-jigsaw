//usr/bin/env jshell --show-version --execution local "$0" "$@"; exit $?

/*
 * Source (compile & execute) `BACH.jsh` script into this jshell session.
 *
 * Scripts can contain any valid code snippets or JShell commands.
 * `BACH.jsh` will load `bach/Bach.java` and define convenient short names
 * for common tasks, like `javac`, `jar` or `java`.
 */
/open BACH.jsh

/*
 * Define global names and paths.
 */
String session = "session-1-jigsaw-intro"
String example = "01_Greetings"
String module  = "com.greetings"
String main    = "com.greetings.Main"
Path src  = Paths.get("..", "..", session, example, "src", module)
Path mods = Paths.get("target", session, example, "mods")

/*
 * Switch bach into verbose mode.
 *
 * In verbose mode bach dumps generated command lines to the configured output
 * stream (defaults to System.out) just before the command are run.
 */
System.setProperty("bach.verbose", "true")

/*
 * Compile the module.
 */

// TODO Compile the source from the example module defined above.

// Hint 1: Use the `java` tool as if you were on the console - with all arguments
// wrapped in brackets. You may pass any Java object instance as an argument, it's
// toString() representation will be added to the underlying command line.
// Hint 2: Use the global constants defined to derive/resolve actual arguments.

/*
 * Run the module/main class.
 */

// TODO Launch the compiled example module. Same hints as above apply.

/exit
