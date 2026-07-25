package io.projectZ.orchestrator.application.port;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 11:31 AM
*/

import io.projectZ.orchestrator.entity.ChatBot;

public interface ChatBotPersistencePort {
    ChatBot getByBotID(String BotID);
    void save(ChatBot chatBot);
    void update(ChatBot chatBot);
}

