package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:14 PM
*/

import io.projectZ.orchestrator.application.port.ConversationPort;
import io.projectZ.orchestrator.entity.Conversation;

import java.util.List;
import java.util.Set;

public interface ConversationService {
    List<Conversation> getAllConversationsByJid(String jid);
    void  saveOrUpdate(Conversation conversation);

}

