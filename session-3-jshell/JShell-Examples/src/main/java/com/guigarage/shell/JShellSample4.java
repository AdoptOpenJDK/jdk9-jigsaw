package com.guigarage.shell;

import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

import java.util.List;

public class JShellSample4 {

    public static void main(String[] args) {
        JShell shell = JShell.builder().build();
        List<SnippetEvent> events = shell.eval("int i = 100;");
        events.stream().forEach(e -> System.out.println(e.toString()));
    }
}
