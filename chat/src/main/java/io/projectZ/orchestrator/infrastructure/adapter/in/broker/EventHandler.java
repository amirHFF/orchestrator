package io.projectZ.orchestrator.infrastructure.adapter.in.broker;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/1/2026 - 6:38 PM
*/

public interface EventHandler<D> {
    void handle(D d);
}

