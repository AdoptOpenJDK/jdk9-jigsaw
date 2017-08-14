package com.guigarage.shell;

import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

import java.util.List;

public class JShellSample3 {

    public static void main(String[] args) {
        JShell shell = JShell.builder().build();
        List<SnippetEvent> events = shell.eval("void helloJShell() { System.out.println(\"hello VJUG\"); }");
        events.stream().forEach(e -> System.out.println(e.toString()));
    }
}
