package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/15/2026 - 6:30 PM
*/

import java.util.List;

public interface ChatBotCacheGateway {
    void saveOrUpdate(ChatBotCacheDTO chatBotCacheDTO);
    void remove(String botId);
    ChatBotCacheDTO get(String botId);
    List<ChatBotCacheDTO> list();
    Boolean getBotHasListener(String botId);
    Boolean getBotEstablishment(String botId);
    void established(String botId);
    void unEstablished(String botId);
    void listenerAssigned(String botId);
    void listenerUnassigned(String botId);

}

