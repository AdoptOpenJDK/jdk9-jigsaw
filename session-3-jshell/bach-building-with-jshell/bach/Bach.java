/* THIS FILE IS GENERATED -- 2017-08-20T05:30:52.291426300Z */
/*
 * Bach - Java Shell Builder
 * Copyright (C) 2017 Christian Stein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// default package

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.util.List;
import java.util.function.*;
import java.util.spi.*;
import java.util.stream.*;

/** Common utilities and helpers. */
interface Basics {

  /** Download the resource specified by its URI to the target directory. */
  static Path download(URI uri, Path targetDirectory) throws IOException {
    return download(uri, targetDirectory, fileName(uri), path -> true);
  }

  /** Download the resource from URI to the target directory using the provided file name. */
  static Path download(URI uri, Path directory, String fileName, Predicate<Path> skip)
      throws IOException {
    URL url = uri.toURL();
    Files.createDirectories(directory);
    Path target = directory.resolve(fileName);
    if (Boolean.getBoolean("bach.offline")) {
      if (Files.exists(target)) {
        return target;
      }
      throw new Error("offline mode is active -- missing file " + target);
    }
    URLConnection urlConnection = url.openConnection();
    FileTime urlLastModifiedTime = FileTime.fromMillis(urlConnection.getLastModified());
    if (urlLastModifiedTime.toMillis() == 0) {
      throw new IOException("last-modified header field not available");
    }
    // TODO log("downloading `%s` [%s]...", url, urlLastModifiedTime);
    if (Files.exists(target)) {
      if (Files.getLastModifiedTime(target).equals(urlLastModifiedTime)) {
        if (Files.size(target) == urlConnection.getContentLengthLong()) {
          if (skip.test(target)) {
            // TODO log("skipped, using `%s`", target);
            return target;
          }
        }
      }
      Files.delete(target);
    }
    // TODO log("transferring `%s`...", uri);
    try (InputStream sourceStream = url.openStream();
        OutputStream targetStream = Files.newOutputStream(target)) {
      sourceStream.transferTo(targetStream);
    }
    Files.setLastModifiedTime(target, urlLastModifiedTime);
    // TODO log("stored `%s` [%s]", target, urlLastModifiedTime);
    return target;
  }

  /** Extract the file name from the uri. */
  static String fileName(URI uri) {
    String urlString = uri.getPath();
    int begin = urlString.lastIndexOf('/') + 1;
    return urlString.substring(begin).split("\\?")[0].split("#")[0];
  }

  static Stream<Path> findDirectories(Path root) {
    try {
      return Files.find(root, 1, (path, attr) -> Files.isDirectory(path))
          .filter(path -> !root.equals(path));
    } catch (Exception e) {
      throw new Error("should not happen", e);
    }
  }

  static Stream<String> findDirectoryNames(Path root) {
    return findDirectories(root).map(root::relativize).map(Path::toString);
  }

  /** Return {@code true} if the path points to a canonical Java archive file. */
  static boolean isJarFile(Path path) {
    if (Files.isRegularFile(path)) {
      return path.getFileName().toString().endsWith(".jar");
    }
    return false;
  }

  /** Return {@code true} if the path points to a canonical Java compilation unit file. */
  static boolean isJavaFile(Path path) {
    if (Files.isRegularFile(path)) {
      String unit = path.getFileName().toString();
      if (unit.endsWith(".java")) {
        return unit.indexOf('.') == unit.length() - 5; // single dot in filename
      }
    }
    return false;
  }

  /** Resolve maven jar artifact. */
  static Path resolve(String group, String artifact, String version) {
    return new Resolvable(group, artifact, version)
        .resolve(Paths.get(".bach", "resolved"), Resolvable.REPOSITORIES);
  }

  /** Extract substring between begin and end tags. */
  static String substring(String string, String beginTag, String endTag) {
    int beginIndex = string.indexOf(beginTag) + beginTag.length();
    int endIndex = string.indexOf(endTag, beginIndex);
    return string.substring(beginIndex, endIndex).trim();
  }

  static void treeDelete(Path root) throws IOException {
    treeDelete(root, path -> true);
  }

  static void treeDelete(Path root, Predicate<Path> filter) throws IOException {
    if (Files.notExists(root)) {
      return;
    }
    try (Stream<Path> stream = Files.walk(root)) {

      List<Path> paths =
          stream
              .filter(p -> !root.equals(p))
              .filter(filter)
              .sorted((p, q) -> -p.compareTo(q))
              .collect(Collectors.toList());
      for (Path path : paths) {
        Files.deleteIfExists(path);
      }
    }
  }

  class Resolvable {

    static final List<String> REPOSITORIES =
        List.of(
            "https://oss.sonatype.org/content/repositories/snapshots",
            "http://repo1.maven.org/maven2",
            "https://jcenter.bintray.com",
            "https://jitpack.io");

    final String group;
    final String artifact;
    final String version;
    final String classifier;
    final String kind;
    final String file;

    Resolvable(String group, String artifact, String version) {
      this.group = group.replace('.', '/');
      this.artifact = artifact;
      this.version = version;
      this.classifier = "";
      this.kind = "jar";
      // assemble file name
      String versifier = classifier.isEmpty() ? version : version + '-' + classifier;
      this.file = artifact + '-' + versifier + '.' + kind;
    }

    boolean isSnapshot() {
      return version.endsWith("SNAPSHOT");
    }

    Path resolve(Path targetDirectory, List<String> repositories) {
      for (String repository : repositories) {
        try {
          return resolve(targetDirectory, repository);
        } catch (IOException e) {
          // e.printStackTrace();
        }
      }
      throw new Error("could not resolve: " + this);
    }

    Path resolve(Path targetDirectory, String repository) throws IOException {
      URI uri = resolveUri(repository);
      String fileName = Basics.fileName(uri);
      // revert local filename with constant version attribute
      if (isSnapshot()) {
        fileName = this.file;
      }
      return download(uri, targetDirectory, fileName, path -> true);
    }

    /** Create uri for specified maven coordinates. */
    URI resolveUri(String repository) {
      String base = repository + '/' + group + '/' + artifact + '/' + version + '/';
      String file = this.file;
      if (isSnapshot()) {
        URI xml = URI.create(base + "maven-metadata.xml");
        try (InputStream sourceStream = xml.toURL().openStream();
            ByteArrayOutputStream targetStream = new ByteArrayOutputStream()) {
          sourceStream.transferTo(targetStream);
          String meta = targetStream.toString("UTF-8");
          String timestamp = Basics.substring(meta, "<timestamp>", "<");
          String buildNumber = Basics.substring(meta, "<buildNumber>", "<");
          file = file.replace("SNAPSHOT", timestamp + '-' + buildNumber);
        } catch (Exception exception) {
          // use file name with "SNAPSHOT" literal
        }
      }
      return URI.create(base + file);
    }

    @Override
    public String toString() {
      return String.format("Resolvable{%s %s %s}", group.replace('/', '.'), artifact, version);
    }
  }
}

/**
 * You can use the foundation JDK tools and commands to create and build applications.
 *
 * @see <a
 *     href="https://docs.oracle.com/javase/9/tools/main-tools-create-and-build-applications.htm">Main
 *     Tools to Create and Build Applications</a>
 */
interface JdkTool {

  /** Command option annotation. */
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  @interface Option {
    String value();
  }

  /** Command builder and tool executor support. */
  class Command {

    final String executable;
    final List<String> arguments = new ArrayList<>();
    private int dumpLimit = Integer.MAX_VALUE;
    private int dumpOffset = Integer.MAX_VALUE;
    private PrintStream out = System.out;
    private PrintStream err = System.err;
    private Map<String, ToolProvider> tools = Collections.emptyMap();

    /** Initialize this command instance. */
    Command(String executable) {
      this.executable = executable;
    }

    /** Add single argument composed of joined path names using {@link File#pathSeparator}. */
    Command add(Collection<Path> paths) {
      return add(paths.stream(), File.pathSeparator);
    }

    /** Add single non-null argument. */
    Command add(Object argument) {
      arguments.add(argument.toString());
      return this;
    }

    /** Add single argument composed of all stream elements joined by specified separator. */
    Command add(Stream<?> stream, String separator) {
      return add(stream.map(Object::toString).collect(Collectors.joining(separator)));
    }

    /** Add all files visited by walking specified root paths recursively. */
    Command addAll(Collection<Path> roots, Predicate<Path> predicate) {
      roots.forEach(root -> addAll(root, predicate));
      return this;
    }

    /** Add all arguments by invoking {@link #add(Object)} for each element. */
    Command addAll(Iterable<?> arguments) {
      arguments.forEach(this::add);
      return this;
    }

    /** Add all files visited by walking specified root path recursively. */
    Command addAll(Path root, Predicate<Path> predicate) {
      try (Stream<Path> stream = Files.walk(root).filter(predicate)) {
        stream.forEach(this::add);
      } catch (IOException e) {
        throw new Error("walking path `" + root + "` failed", e);
      }
      return this;
    }

    /** Add all reflected options. */
    Command addAllOptions(Object options) {
      return addAllOptions(options, UnaryOperator.identity());
    }

    /** Add all reflected options after a custom stream operator did its work. */
    Command addAllOptions(Object options, UnaryOperator<Stream<java.lang.reflect.Field>> operator) {
      Stream<java.lang.reflect.Field> stream =
          Arrays.stream(options.getClass().getDeclaredFields())
              .filter(field -> !field.isSynthetic())
              .filter(field -> !java.lang.reflect.Modifier.isStatic(field.getModifiers()))
              .filter(field -> !java.lang.reflect.Modifier.isPrivate(field.getModifiers()))
              .filter(field -> !java.lang.reflect.Modifier.isTransient(field.getModifiers()));
      stream = operator.apply(stream);
      stream.forEach(field -> addOptionUnchecked(options, field));
      return this;
    }

    private void addOption(Object options, Field field) throws IllegalAccessException {
      // custom option visitor method declared?
      try {
        options.getClass().getDeclaredMethod(field.getName(), Command.class).invoke(options, this);
        return;
      } catch (NoSuchMethodException e) {
        // fall-through
      } catch (InvocationTargetException e) {
        throw new Error(e);
      }
      // get or generate option name
      Optional<Option> optional = Optional.ofNullable(field.getAnnotation(Option.class));
      String optionName = optional.map(Option::value).orElse(generateName(field.getName()));
      // is it an omissible boolean flag?
      if (field.getType() == boolean.class) {
        if (field.getBoolean(options)) {
          add(optionName);
        }
        return;
      }
      Object value = field.get(options);
      // skip null field value
      if (value == null) {
        return;
      }
      // skip empty collections
      if (value instanceof Collection && ((Collection) value).isEmpty()) {
        return;
      }
      // add option name only if it is not empty
      if (!optionName.isEmpty()) {
        add(optionName);
      }
      // is value a collection of paths?
      if (value instanceof Collection) {
        try {
          @SuppressWarnings("unchecked")
          Collection<Path> path = (Collection<Path>) value;
          add(path);
          return;
        } catch (ClassCastException e) {
          // fall-through
        }
      }
      // is value a charset?
      if (value instanceof Charset) {
        add(((Charset) value).name());
        return;
      }
      // finally, add string representation of the value
      add(value.toString());
    }

    private void addOptionUnchecked(Object options, java.lang.reflect.Field field) {
      try {
        addOption(options, field);
      } catch (ReflectiveOperationException e) {
        throw new Error("reflecting option from field '" + field + "' failed for " + options, e);
      }
    }

    static String generateName(String name) {
      boolean hasUppercase = !name.equals(name.toLowerCase());
      StringBuilder defaultName = new StringBuilder();
      if (hasUppercase) {
        defaultName.append("--");
        name.chars()
            .forEach(
                i -> {
                  if (Character.isUpperCase(i)) {
                    defaultName.append('-');
                    defaultName.append((char) Character.toLowerCase(i));
                  } else {
                    defaultName.append((char) i);
                  }
                });
      } else {
        defaultName.append('-');
        defaultName.append(name.replace('_', '-'));
      }
      return defaultName.toString();
    }

    /** Dump command executables and arguments using the provided string consumer. */
    Command dump(Consumer<String> consumer) {
      ListIterator<String> iterator = arguments.listIterator();
      consumer.accept(executable);
      while (iterator.hasNext()) {
        String argument = iterator.next();
        int nextIndex = iterator.nextIndex();
        String indent = nextIndex > dumpOffset || argument.startsWith("-") ? "" : "  ";
        consumer.accept(indent + argument);
        if (nextIndex > dumpLimit) {
          int last = arguments.size() - 1;
          int diff = last - nextIndex;
          if (diff > 1) {
            consumer.accept(indent + "... [omitted " + diff + " arguments]");
          }
          consumer.accept(indent + arguments.get(last));
          break;
        }
      }
      return this;
    }

    /** Set dump offset and limit. */
    Command mark(int limit) {
      if (limit < 0) {
        throw new IllegalArgumentException("limit must be greater then zero: " + limit);
      }
      this.dumpOffset = arguments.size();
      this.dumpLimit = arguments.size() + limit;
      return this;
    }

    Command setStandardStreams(PrintStream out, PrintStream err) {
      this.out = out;
      this.err = err;
      return this;
    }

    Command setToolProvider(ToolProvider tool) {
      if (tools == Collections.EMPTY_MAP) {
        tools = new TreeMap<>();
      }
      tools.put(tool.name(), tool);
      return this;
    }

    /** Create new argument array based on this command's arguments. */
    String[] toArgumentsArray() {
      return arguments.toArray(new String[arguments.size()]);
    }

    /** Create new {@link ProcessBuilder} instance based on this command setup. */
    ProcessBuilder toProcessBuilder() {
      List<String> strings = new ArrayList<>(1 + arguments.size());
      strings.add(executable);
      strings.addAll(arguments);
      ProcessBuilder processBuilder = new ProcessBuilder(strings);
      processBuilder.redirectErrorStream(true);
      return processBuilder;
    }

    /** Run this command. */
    void run() {
      int result = run(UnaryOperator.identity(), this::toProcessBuilder);
      boolean successful = result == 0;
      if (successful) {
        return;
      }
      throw new AssertionError("expected an exit code of zero, but got: " + result);
    }

    /** Run this command. */
    int run(UnaryOperator<ToolProvider> operator, Supplier<ProcessBuilder> supplier) {
      if (Boolean.getBoolean("bach.verbose")) {
        out.println(" . ");
        dump(line -> out.println(" | " + line));
        out.println(" ' ");
      }
      ToolProvider systemTool = ToolProvider.findFirst(executable).orElse(null);
      ToolProvider tool = tools.getOrDefault(executable, systemTool);
      if (tool != null) {
        return operator.apply(tool).run(out, err, toArgumentsArray());
      }
      ProcessBuilder processBuilder = supplier.get();
      try {
        Process process = processBuilder.start();
        process.getInputStream().transferTo(out);
        return process.waitFor();
      } catch (IOException | InterruptedException e) {
        throw new Error("executing `" + executable + "` failed", e);
      }
    }
  }

  /**
   * You can use the javac tool and its options to read Java class and interface definitions and
   * compile them into bytecode and class files.
   *
   * @see <a href="https://docs.oracle.com/javase/9/tools/javac.htm">javac</a>
   */
  class Javac implements JdkTool {
    /** (Legacy) class path. */
    List<Path> classPath = List.of();

    /** (Legacy) locations where to find Java source files. */
    @Option("--source-path")
    transient List<Path> classSourcePath = List.of();

    /** Generates all debugging information, including local variables. */
    @Option("-g")
    boolean generateAllDebuggingInformation = false;

    /** Output source locations where deprecated APIs are used. */
    boolean deprecation = true;

    /** The destination directory for class files. */
    @Option("-d")
    Path destinationPath = null;

    /** Specify character encoding used by source files. */
    Charset encoding = StandardCharsets.UTF_8;

    /** Terminate compilation if warnings occur. */
    @Option("-Werror")
    boolean failOnWarnings = true;

    /** Specify where to find application modules. */
    List<Path> modulePath = List.of();

    /** Where to find input source files for multiple modules. */
    List<Path> moduleSourcePath = List.of();

    /** Generate metadata for reflection on method parameters. */
    boolean parameters = true;

    /** Output messages about what the compiler is doing. */
    boolean verbose = false;

    /** Create javac command with options and source files added. */
    @Override
    public Command toCommand() {
      Command command = JdkTool.super.toCommand();
      command.mark(10);
      command.addAll(classSourcePath, Basics::isJavaFile);
      command.addAll(moduleSourcePath, Basics::isJavaFile);
      return command;
    }
  }

  /**
   * You can use the java command to launch a Java application.
   *
   * @see <a href="https://docs.oracle.com/javase/9/tools/java.htm">java</a>
   */
  class Java implements JdkTool {
    /**
     * Creates the VM but doesn't execute the main method.
     *
     * <p>This {@code --dry-run} option may be useful for validating the command-line options such
     * as the module system configuration.
     */
    boolean dryRun = false;

    /** Where to find application modules. */
    List<Path> modulePath = List.of();

    /** Initial module to resolve and the name of the main class to execute. */
    @Option("--module")
    String module = null;
  }

  /**
   * You use the javadoc tool and options to generate HTML pages of API documentation from Java
   * source files.
   *
   * @see <a href="https://docs.oracle.com/javase/9/tools/javadoc.htm">javadoc</a>
   */
  class Javadoc implements JdkTool {
    /** Shuts off messages so that only the warnings and errors appear. */
    boolean quiet = true;
  }

  /**
   * You can use the jar command to create an archive for classes and resources, and manipulate or
   * restore individual classes or resources from an archive.
   *
   * @see <a href="https://docs.oracle.com/javase/9/tools/jar.htm">jar</a>
   */
  class Jar implements JdkTool {
    /** Specify the operation mode for the jar command. */
    @Option("")
    String mode = "--create";

    /** Specifies the archive file name. */
    @Option("--file")
    Path file = Paths.get("out.jar");

    /** Specifies the application entry point for stand-alone applications. */
    String mainClass = null;

    /** Specifies the module version, when creating a modular JAR file. */
    String moduleVersion = null;

    /** Stores without using ZIP compression. */
    boolean noCompress = false;

    /** Sends or prints verbose output to standard output. */
    @Option("--verbose")
    boolean verbose = false;

    /** Changes to the specified directory and includes the files at the end of the command. */
    @Option("-C")
    Path path = null;
  }

  /**
   * You use the jdeps command to launch the Java class dependency analyzer.
   *
   * @see <a href="https://docs.oracle.com/javase/9/tools/jdeps.htm">jdeps</a>
   */
  class Jdeps implements JdkTool {
    /** Specifies where to find class files. */
    List<Path> classpath = List.of();

    /** Recursively traverses all dependencies. */
    boolean recursive = true;

    /** Finds class-level dependencies in JDK internal APIs. */
    boolean jdkInternals = false;

    /** Shows profile or the file containing a package. */
    boolean profile = false;

    /** Restricts analysis to APIs, like deps from the signature of public and protected members. */
    boolean apionly = false;

    /** Prints dependency summary only. */
    boolean summary = false;

    /** Prints all class-level dependencies. */
    boolean verbose = false;
  }

  /**
   * You can use the jlink tool to assemble and optimize a set of modules and their dependencies
   * into a custom runtime image.
   *
   * @see <a href="https://docs.oracle.com/javase/9/tools/jlink.htm">jlink</a>
   */
  class Jlink implements JdkTool {
    /** Where to find application modules. */
    List<Path> modulePath = List.of();

    /** The directory that contains the resulting runtime image. */
    @Option("--output")
    Path output = null;
  }

  /** Name of this tool, like {@code javac} or {@code jar}. */
  default String name() {
    return getClass().getSimpleName().toLowerCase();
  }

  /**
   * Call any executable tool by its name and add all arguments as single elements.
   *
   * @throws AssertionError if the execution result is not zero
   */
  static void run(String executable, Object... arguments) {
    new Command(executable).addAll(List.of(arguments)).run();
  }

  /**
   * Execute this tool with all options and arguments applied.
   *
   * @throws AssertionError if the execution result is not zero
   */
  default void run() {
    toCommand().run();
  }

  /** Create command instance based on this tool's options. */
  default Command toCommand() {
    return new Command(name()).addAllOptions(this);
  }
}

/**
 * Java Shell Builder.
 *
 * @see <a href="https://github.com/sormuras/bach">https://github.com/sormuras/bach</a>
 */
class Bach {

  static void generateJShellScript(Path directory, String name) throws Exception {
    Path file = directory.resolve(name);
    List<String> script =
        List.of(
            "// JdkTool",
            "void java(Object... args) { JdkTool.run(\"java\", args); }",
            "void javac(Object... args) { JdkTool.run(\"javac\", args); }");
    Files.createDirectories(directory);
    Files.write(file, script);
  }
}
