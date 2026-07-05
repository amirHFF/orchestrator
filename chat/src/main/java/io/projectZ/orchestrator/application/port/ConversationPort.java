package io.projectZ.orchestrator.application.port;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:02 PM
*/

import io.projectZ.orchestrator.entity.Conversation;

import java.util.List;
import java.util.Set;

public interface ConversationPort {
    void save(Conversation conversation);
    void update(Conversation conversation);
    List<Conversation> getConversationsByJid(String jid);
    Conversation getConversationByJids(String jid, String targetJid);

}
