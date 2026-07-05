package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:02 PM
*/

import io.projectZ.orchestrator.application.port.ConversationPort;
import io.projectZ.orchestrator.entity.Conversation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


@Service
public class ConversationServiceImpl implements ConversationService {
    private final static String jidPostfix = "@zchat.ir";
    private final ConversationPort conversationPort;

    public ConversationServiceImpl(ConversationPort conversationPort) {
        this.conversationPort = conversationPort;
    }

    @Override
    public List<Conversation> getAllConversationsByJid(String jid) {
        if (jid == null) {
            throw new IllegalArgumentException("jid is null");
        }
        if (!jid.contains(jidPostfix)) {
            jid = jid.concat(jidPostfix);
        }

        List<Conversation> conversations = conversationPort.getConversationsByJid(jid);
        conversations.sort(Comparator.comparing(Conversation::getLastMessageTime));
        return conversations;
    }

    @Override
    public void saveOrUpdate(Conversation conversation) {
        if (conversation != null) {
            if (!conversation.getJid().contains(jidPostfix)) {
                conversation.setJid(conversation.getJid().concat(jidPostfix));
            }
            if (!conversation.getTargetJid().contains(jidPostfix)) {
                conversation.setTargetJid(conversation.getTargetJid().concat(jidPostfix));
            }
        }
        Conversation loadedConversation =  conversationPort.getConversationByJids(conversation.getJid() , conversation.getTargetJid());
        if (loadedConversation == null) {
            conversationPort.save(conversation);
        }
        else if (!loadedConversation.getLastMessage().equals(conversation.getLastMessage())){
            loadedConversation.setLastMessage(conversation.getLastMessage());
            conversationPort.update(loadedConversation);
        }
    }
}

