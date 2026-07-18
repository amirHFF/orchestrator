package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 7:28 PM
*/

import io.projectZ.orchestrator.entity.ChatMessage;

public interface ChatMessageHandler {
    void handleReceivedMessage(ChatMessage chatMessage);
    void sendMessage(ChatMessage message);
}

