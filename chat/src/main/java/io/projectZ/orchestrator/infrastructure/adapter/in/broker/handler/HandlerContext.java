package io.projectZ.orchestrator.infrastructure.adapter.in.broker.handler;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/6/2026 - 9:39 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.EventDTO;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.UserProfileEventDto;
import org.springframework.stereotype.Component;

public class HandlerContext {
    private EventHandler eventHandler;
    public void setStrategy(EventHandler handler){
        eventHandler = handler;
    }
    public void executeHandle(EventDTO eventDTO){
        if (eventDTO instanceof UserProfileEventDto userEventDto) {
            eventHandler.handle(userEventDto);
        }
    }
}

