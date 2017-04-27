package com.guigarage.shell;

import jdk.jshell.JShell;
import jdk.jshell.Snippet;

public class JShellSample1 {

    public static void main(String[] args) {
        JShell shell = JShell.builder().idGenerator((snippet, integer) -> "MyID" + integer).build();
        shell.eval("int a = 100;");
        shell.eval("100 + 100;");
        shell.variables().forEach(v -> System.out.println(v.typeName() + " " + v.name() +  " " + v.id()));
    }
}
