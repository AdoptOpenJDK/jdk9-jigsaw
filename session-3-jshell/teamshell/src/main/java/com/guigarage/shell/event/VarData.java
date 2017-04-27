package com.guigarage.shell.event;

import java.io.Serializable;

public class VarData implements Serializable {

    private final String name;

    private final String content;

    private final String type;

    public VarData(String type, String name, String content) {
        this.name = name;
        this.content = content;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }
}
