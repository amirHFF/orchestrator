package io.projectZ.orchestrator.application.port;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 6:01 PM
*/

import io.projectZ.orchestrator.entity.ChatMessage;

public interface ChatPort {
    void sendMessage(ChatMessage message);
}
