package io.projectZ.orchestrator.ai.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:03 AM
*/

import io.projectZ.orchestrator.ai.model.AiProxyModel;
import io.projectZ.orchestrator.ai.model.PromptModel;

public interface AiCoordinator<E> {
    E processMessage(AiProxyModel actorModel , String message);
}

