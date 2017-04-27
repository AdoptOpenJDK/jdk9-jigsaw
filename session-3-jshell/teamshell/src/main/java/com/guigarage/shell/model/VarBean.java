package com.guigarage.shell.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean
public class VarBean {

    private Property<String> name;

    private Property<String> content;

    private Property<String> type;

    public String getName() {
        return name.get();
    }

    public Property<String> nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getContent() {
        return content.get();
    }

    public Property<String> contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getType() {
        return type.get();
    }

    public Property<String> typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
