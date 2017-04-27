package com.guigarage.shell;

import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.VarSnippet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by hendrikebbers on 22.04.17.
 */
public class JShellSample6 {

    public static void main(String[] args) throws Exception {
        JShell shell = JShell.builder().
                build();
        shell.eval(readFile(JShellSample6.class.getResource("commands.repl").getPath())).forEach(e -> System.out.println(e));
    }

    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

}
