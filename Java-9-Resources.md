JDK9 EA Download 
	https://jdk9.java.net/jigsaw/	

JDK 9 Installation
	https://docs.oracle.com/javase/9/install/overview-jdk-9-and-jre-9-installation.htm#JSJIG-GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A

JDK 9 Jigsaw page
	http://openjdk.java.net/projects/jigsaw/

JSRs involved
	376: Java Platform Module System  - specified some of the below JEPs

JEPs involved
	Module system of the Java platform as specified by project Jigsaw JEPs covered:
		200: The Modular JDK
			201: Modular Source Code
				220: Modular Run-Time Images (depends on jEP 162)
			260	: Encapsulate Most Internal APIs (no dependencies)
			261: Module System (dependens on JEP 220, related to JEP 238)
			282: jlink: The Java Linker (replaces implementation of JEP 220)
			275: Modular Java Application Packaging
		222: jshell: The Java Shell (Read-Eval-Print Loop)
		G1 related JEPs (3 of them)
		193: Variable Handles
		110: HTTP 2 Client
		238: Multi-Release JAR Files
		JEPs related to a number of security improvements
		158: Unified JVM Logging
		JEPs related to a number of platform improvements
		Many, many more JEPs

JDK 9 Outreach
	https://wiki.openjdk.java.net/display/Adoption/JDK+9+Outreach

JDK 9 Quality Outreach (we could use your help)
	https://wiki.openjdk.java.net/display/quality/Quality+Outreach

Java 9:  REPL
	https://www.voxxed.com/blog/2016/10/just-enough-code-hacking-jdk
	https://www.voxxed.com/blog/2016/09/java-9-series-jshell/
	https://www.voxxed.com/blog/2015/04/what-the-upcoming-repl-will-mean-for-java-people/
	https://www.voxxed.com/blog/2015/07/a-sneak-peek-at-java-9-sleeper-feature-repl-and-jshell/

Java 9:  Jigsaw
	Useful topics
		https://www.sitepoint.com/java-9-resources/?utm_source=Sociallymap&utm_medium=Sociallymap&utm_campaign=Sociallymap

	Preparing for JDK 9
		https://www.voxxed.com/blog/interview/java-9-coming-nicolai-parlog/
		https://www.voxxed.com/blog/2016/03/13285/
		https://www.voxxed.com/blog/2014/09/preparing-for-java-the-modular-edition/
		
		http://blog.codefx.org/java/dev/javaone-2015-prepare-for-jdk-9/
			[blog] http://blog.codefx.org/java/dev/javaone-2015-introduction-to-modular-development/
			[video] https://www.youtube.com/watch?v=eALw4P_0O4k - Introduction to Modular Development by Alan Bateman (3 months)
			[video] https://www.youtube.com/watch?v=Ks7J_qQVR7Y - Project Jigsaw in JDK 9: Modularity Comes To Java - Simon Ritter (3 months old)
			Prepare JDK 9
				https://www.youtube.com/watch?v=eU8hCCjGSbE
			Prepare for JDK 9 by haocheng - JCConf 2016 R0 Day2-4
				https://www.youtube.com/watch?v=S91fn0dFTTY
			Simon Ritter - Preparing for JDK 9 with Zulu and OpenJDK
				https://www.youtube.com/watch?v=i9bnV0KKyAA 
			180.Course Preview- Java 9 Modularity- First Look.mp4
				https://www.youtube.com/watch?v=0UD3KIwXwJw
	
	New features
		Summary of features
			http://www.baeldung.com/new-java-9
			https://docs.oracle.com/javase/9/whatsnew/toc.htm#JSNEW-GUID-656B84C8-2498-4CD0-B20D-8C9AC633746B
			https://www.voxxed.com/blog/2015/07/the-features-project-jigsaw-brings-to-java-9/

		https://www.voxxed.com/blog/2015/12/project-jigsaw-hands-on-guide
		https://www.voxxed.com/blog/2015/06/jumping-into-java-9-new-build-ready-to-test/
		https://www.voxxed.com/blog/2016/09/java-9-weak-modules/	

		Java 9: Encapsulate Most Internal APIs
			https://www.voxxed.com/blog/2016/11/java-9-series-encapsulate-internal-apis/

		Java 9: Multi-Release Jar
			https://www.voxxed.com/blog/2016/11/java-9-series-multi-release-jar-files/

		Java 9: Concurrency updates
			https://www.voxxed.com/blog/2016/10/java-9-series-concurrency-updates/

		Java 9: Variable Handles
			https://www.voxxed.com/blog/2016/11/java-9-series-variable-handles/

		Java 9:  Http/2 Client
			https://www.voxxed.com/blog/2016/10/java-9-series-http2-client/

		Java 9:  Compact Strings
			https://www.voxxed.com/blog/2015/11/london-java-community-holds-strong-in-jcp-election/

		Java 9:  Try-with-resources feature
			https://www.voxxed.com/blog/2015/01/new-try-resources-improvement-jdk-9/

		Java 9:  Compact profiles
			https://www.voxxed.com/blog/2014/12/jdeps-compact-profiles-java-modularity/

		Java 9:  Streams
			https://www.voxxed.com/blog/2017/02/java-9-streams-api/

		Java 9:  Convenience Factory Methods
			https://www.voxxed.com/blog/2017/01/java-9-series-factory-methods-collections/

		Java 9:  Segmented Code Cache
			https://www.voxxed.com/blog/2016/11/java-9-series-segmented-code-cache/

		Java 9: JVM
			https://www.voxxed.com/blog/2016/10/java-9-series-jvm/

		Java 9: Unsafe
			https://www.voxxed.com/blog/2015/08/java-handlers-propose-encapulating-fix-for-unsafe-debacle/
			https://www.voxxed.com/blog/2015/07/will-removal-of-sun-misc-unsafe-trigger-javapocalypse/

		Java 9: Augment Use-Site…, Enums…, Lambda Leftovers
			https://www.voxxed.com/blog/2016/12/three-new-jeps/

		Java 9: Panama updates
			https://www.voxxed.com/blog/2016/01/sailing-far-shores-project-panama-makes-progress/

		Java 9: AOT
			https://www.voxxed.com/blog/2016/10/native-ahead-time-compilation-java/

		Java 9/10: var or val
			https://www.voxxed.com/blog/2016/09/var-comes-to-java/

	Cons of Java 9 modularity (the other side of things)
		https://www.voxxed.com/blog/2015/12/will-there-be-module-hell/
		https://www.voxxed.com/blog/2015/05/will-java-9-mess-up-your-code
		https://www.voxxed.com/blog/2016/11/problem-modules-reflective-access/
		https://www.voxxed.com/blog/2016/11/issues-with-the-module-system/
		https://www.voxxed.com/blog/2015/05/will-java-9-mess-up-your-code/
		https://blog.plan99.net/is-jigsaw-good-or-is-it-wack-ec634d36dd6f

	Advanced Modular Development
		[blog] http://blog.codefx.org/java/dev/javaone-2015-advanced-modular-development/
		[video] https://www.youtube.com/watch?v=WJHjKMIrbD0 (5 months old)
	
		http://blog.codefx.org/java/dev/javaone-2015-under-the-hood-of-project-jigsaw/%22
		https://www.voxxed.com/blog/presentation/presentation-java-9-make-way-for-modules/
		[video] https://www.youtube.com/watch?v=IA8FkrY0VFE - Modular Development with JDK 9
		https://www.voxxed.com/blog/presentation/presentation-java-9-modularity-action/
			Java 9 Modularity in Action
				https://www.youtube.com/watch?v=CoXueufCdtY
			The Java Module System In Action (Nicolai Parlog, Germany)
				https://www.youtube.com/watch?v=0M72pH2rXwA

Java 9: Migration
	https://docs.oracle.com/javase/9/migrate/toc.htm#JSMIG-GUID-7744EF96-5899-4FB2-B34E-86D49B2E89B6

Java 9: Tool reference
	https://docs.oracle.com/javase/9/tools/tools-and-command-reference.htm#JSWOR596

Java 9: JavaDoc (API documentation)
	http://download.java.net/java/jdk9/docs/api/overview-summary.html

Java 9: Developer Guides
	https://docs.oracle.com/javase/9/javase-docs.htm#

Java 9:  Quick Start guide
	http://openjdk.java.net/projects/jigsaw/quick-start