package io.projectZ.orchestrator.ai.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:03 AM
*/

import io.projectZ.orchestrator.ai.model.AiActorModel;

public interface AiCoordinator<E> {
    E processMessage(AiActorModel actorModel ,String message);
}

