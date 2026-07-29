package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 10:49 AM
*/

import io.projectZ.orchestrator.entity.ChatBot;

public interface ChatBotService {
    ChatBot get(String BotID);
    ChatBot save(ChatBot chatBot);
    ChatBot update(ChatBot chatBot);
    void start(String BotID);
}
