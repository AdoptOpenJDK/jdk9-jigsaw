package com.guigarage.shell.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean
public class ShellBean {

    private Property<String> commandline;

    private ObservableList<VarBean> variables;

    public String getCommandline() {
        return commandline.get();
    }

    public Property<String> commandlineProperty() {
        return commandline;
    }

    public void setCommandline(String commandline) {
        this.commandline.set(commandline);
    }

    public ObservableList<VarBean> getVariables() {
        return variables;
    }

    public void setVariables(ObservableList<VarBean> variables) {
        this.variables = variables;
    }
}
