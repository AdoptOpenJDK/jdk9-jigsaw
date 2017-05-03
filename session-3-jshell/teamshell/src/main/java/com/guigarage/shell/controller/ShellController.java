package com.guigarage.shell.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.dolphin.server.DolphinAction;
import com.canoo.dolphin.server.DolphinController;
import com.canoo.dolphin.server.DolphinModel;
import com.canoo.dolphin.server.event.DolphinEventBus;
import com.guigarage.shell.event.Topics;
import com.guigarage.shell.event.VarData;
import com.guigarage.shell.model.ShellBean;
import com.guigarage.shell.model.VarBean;
import com.guigarage.shell.service.ShellService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@DolphinController("ShellController")
public class ShellController {

    @DolphinModel
    private ShellBean model;

    @Autowired
    private BeanManager beanManager;

    @Autowired
    private DolphinEventBus eventBus;

    @Autowired
    private ShellService shellService;

    @PostConstruct
    public void init() {
        eventBus.subscribe(Topics.VAR_CREATED_TOPIC, e -> {
            add(e.getData());
        });
        shellService.getVariables().forEach(v -> add(v));
    }

    private void add(VarData data) {
        VarBean bean = beanManager.create(VarBean.class);
        bean.setName(data.getName());
        bean.setType(data.getType());
        bean.setContent(data.getContent());
        model.getVariables().add(bean);
    }

    @DolphinAction("eval")
    public void eval() {
        shellService.eval(model.getCommandline());
    }
}
