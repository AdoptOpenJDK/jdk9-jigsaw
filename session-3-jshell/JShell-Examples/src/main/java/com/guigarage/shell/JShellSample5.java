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

public class JShellSample5 {

    public static void main(String[] args) throws Exception {
        JShell shell = JShell.builder().
                idGenerator((snippet, integer) -> "My ID " + integer).
                out(new PrintStream(new FileOutputStream("out.log"), true, "UTF-8")).
                err(new PrintStream(new FileOutputStream("error.log"), true, "UTF-8")).
                build();
        print(shell.eval("int a = 50;"));
    }

    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }

    public static void print(List<SnippetEvent> events) {
        for(SnippetEvent event : events) {
            if(event.snippet() instanceof VarSnippet) {
                String name = ((VarSnippet) event.snippet()).name();
                String id =  ((VarSnippet) event.snippet()).id();
                System.out.println("Variable " + name + " defined with ID " + id);
            }
        }
    }

}
