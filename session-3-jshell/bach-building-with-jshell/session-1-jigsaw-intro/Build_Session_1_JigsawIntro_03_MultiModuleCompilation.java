import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** Multi module compilation using basic tools from {@code Bach.java} */
class Build_Session_1_JigsawIntro_03_MultiModuleCompilation {

  private static final String session = "session-1-jigsaw-intro";
  private static final String example = "03_MultiModuleCompilation";
  private static final Path source = Paths.get("..", "..", session, example, "src");
  private static final Path modules = Paths.get("target", session, example, "mods");

  /**
   * Entry-point for the build.
   *
   * <p>The entry-point may be invoked by external programs, like IDEs and {@code jshell}.
   *
   * @param args build arguments - unused here
   */
  public static void main(String... args) {
    /*
     * Let bach print what is going on at the moment.
     */
    System.setProperty("bach.verbose", "true");

    /*
     * Create and configure `Javac` instance.
     *
     * The property names declared are the camel-case variants of the command line tool pendants.
     *
     * Line by line explanation:
     *
     * - JdkTool.Javac javac = new JdkTool.Javac();
     *
     *   Create Javac instance. It is initialized with (opinionated) default
     *   option values. See command dump below.
     *
     *
     * - javac.moduleSourcePath = // TODO
     *
     *   Define single Path instance (`source`) as the module path. This is
     *   where `javac` finds input source files for multiple modules. It is
     *   used by the terminal `run()` method, too.
     *
     *
     * - javac.destinationPath = // TODO
     *
     *   Set destination directory for compiled modules files.
     *
     *
     * - // TODO
     *
     *   Execute `javac` with configured options applied and all .java files
     *   found by walking `javac.moduleSourcePath` recursively added.
     *
     *
     * Expected command dump:
     *  .
     *  | javac
     *  | -deprecation
     *  | -d
     *  |   target\session-1-jigsaw-intro\03_MultiModuleCompilation\mods
     *  | -encoding
     *  |   UTF-8
     *  | -Werror
     *  | --module-source-path
     *  |   ..\..\session-1-jigsaw-intro\03_MultiModuleCompilation\src
     *  | -parameters
     *  | ..\..\session-1-jigsaw-intro\03_MultiModuleCompilation\src\com.greetings\com\greetings\Main.java
     *  | ..\..\session-1-jigsaw-intro\03_MultiModuleCompilation\src\com.greetings\module-info.java
     *  | ..\..\session-1-jigsaw-intro\03_MultiModuleCompilation\src\org.astro\module-info.java
     *  | ..\..\session-1-jigsaw-intro\03_MultiModuleCompilation\src\org.astro\org\astro\World.java
     *  '
     */
    JdkTool.Javac javac = new JdkTool.Javac();

    // TODO assign properties to the `javac` instance fields
    // TODO launch `javac`

    /*
     * Start the module.
     *
     * Expected command dump:
     *  .
     *  | java
     *  | -p
     *  |   target\session-1-jigsaw-intro\03_MultiModuleCompilation\mods
     *  | -m
     *  |   com.greetings/com.greetings.Main
     *  '
     */

    // TODO Launch the compiled module using either by invoking the static `run` method of `JdkTool`
    // or by creating, configuring and running a `JdkTool.Java` instance.

  }
}
