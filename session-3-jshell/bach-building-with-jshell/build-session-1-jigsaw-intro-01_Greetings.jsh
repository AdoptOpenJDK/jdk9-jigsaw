//usr/bin/env jshell --show-version --execution local "$0" "$@"; exit $?

/*
 * Source (compile & execute) `Bach.jsh` script into this jshell session.
 *
 * Scripts can contain any valid code snippets or JShell commands.
 * `Bach.jsh` will load `bach/Bach.java` and define convenient short names
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
javac("-d", mods, src.resolve("module-info.java"), src.resolve("com/greetings/Main.java"))

/*
 * Run the module/main class.
 */
java("--module-path", mods, "--module", module + "/" + main)

/exit
