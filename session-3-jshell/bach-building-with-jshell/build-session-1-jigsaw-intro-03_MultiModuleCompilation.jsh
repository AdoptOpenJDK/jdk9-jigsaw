//usr/bin/env jshell --show-version --execution local "$0" "$@"; exit $?

/*
 * Source (compile & execute) `BACH.jsh` script into this jshell session.
 */
/open BACH.jsh

/*
 * Source (compile) `Build_Session_1_JigsawIntro_03_MultiModuleCompilation.java` into this jshell session.
 */
/open session-1-jigsaw-intro/Build_Session_1_JigsawIntro_03_MultiModuleCompilation.java

/*
 * Invoke the static `main(String...)` method on our build class w/o any argument.
 */
Build_Session_1_JigsawIntro_03_MultiModuleCompilation.main()

/*
 * Close this jshell session.
 */
/exit
