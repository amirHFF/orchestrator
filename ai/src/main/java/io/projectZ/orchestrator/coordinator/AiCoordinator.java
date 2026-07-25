package io.projectZ.orchestrator.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:03 AM
*/

import io.projectZ.orchestrator.controller.dto.request.ChatTalkRequest;

public interface AiCoordinator<E> {
    E processMessage(ChatTalkRequest chatTalk , String sessionId);
}

