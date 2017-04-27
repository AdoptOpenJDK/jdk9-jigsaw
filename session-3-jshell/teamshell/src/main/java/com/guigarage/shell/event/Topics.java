package com.guigarage.shell.event;

import com.canoo.dolphin.server.event.Topic;

public interface Topics {

    Topic<VarData> VAR_CREATED_TOPIC = Topic.create();

}
