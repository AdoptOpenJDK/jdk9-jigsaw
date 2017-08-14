package com.guigarage.shell.service;

import com.canoo.dolphin.server.event.DolphinEventBus;
import com.guigarage.shell.event.Topics;
import com.guigarage.shell.event.VarData;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.VarSnippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Service
public class ShellService {

    private JShell shell;

    private List<VarData> variables = new CopyOnWriteArrayList<>();

    @Autowired
    private DolphinEventBus eventBus;

    @PostConstruct
    public void init() {
        shell = JShell.builder().build();
    }

    public Stream<VarData> getVariables() {
        return variables.stream();
    }

    public synchronized void eval(String command) {
        List<SnippetEvent> events = shell.eval(command);
        for(SnippetEvent event : events) {
            if(event.snippet() instanceof VarSnippet) {
                String type = ((VarSnippet) event.snippet()).typeName();
                String name = ((VarSnippet) event.snippet()).name();
                String value = event.value();
                VarData varData = new VarData(type, name, value);
                eventBus.publish(Topics.VAR_CREATED_TOPIC, varData);
                variables.add(varData);
            }
        }
    }
}
