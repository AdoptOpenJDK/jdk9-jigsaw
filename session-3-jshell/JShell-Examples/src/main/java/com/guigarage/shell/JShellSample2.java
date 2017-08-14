package com.guigarage.shell;

import jdk.jshell.JShell;

public class JShellSample2 {

    public static void main(String[] args) {
        JShell shell = JShell.builder().build();
        shell.eval("void helloJShell() { System.out.println(\"hello VJUG\"); }");
        shell.methods().forEach(v -> System.out.println("Method: " + v.signature() + " " + v.name()));
    }
}
