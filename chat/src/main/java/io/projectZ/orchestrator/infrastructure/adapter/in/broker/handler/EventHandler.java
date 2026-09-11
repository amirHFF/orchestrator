package io.projectZ.orchestrator.infrastructure.adapter.in.broker.handler;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/1/2026 - 6:38 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.EventDTO;

public interface EventHandler {
    void handle(EventDTO eventDTO);
}

