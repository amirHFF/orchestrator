package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:02 PM
*/

import io.projectZ.orchestrator.application.port.ConversationPort;
import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.KeycloakAdminClientTemp;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.UserRepresentation;
import io.projectZ.orchestrator.infrastructure.config.SecurityConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConversationServiceImpl implements ConversationService {

    //todo: postfix must be deleted from front point of view
    private final static String jidPostfix = "@zchat.ir";
    private final ConversationPort conversationPort;
    private final KeycloakAdminClientTemp keycloakAdminClientTemp;

    public ConversationServiceImpl(ConversationPort conversationPort, KeycloakAdminClientTemp keycloakAdminClientTemp) {
        this.conversationPort = conversationPort;
        this.keycloakAdminClientTemp = keycloakAdminClientTemp;
    }

    @Override
    public List<Conversation> getAllConversationsByJid(String jid) {
        if (jid == null) {
            throw new IllegalArgumentException("jid is null");
        }
        if (!jid.contains(jidPostfix)) {
            jid = jid.concat(jidPostfix);
        }

        List<Conversation> conversations = conversationPort.getAllConversationsByJid(jid);
        conversations.sort(Comparator.comparing(Conversation::getLastMessageTime));
        return conversations;
    }

    @Override
//    @Transactional
    public void saveOrUpdate(Conversation conversation) {
        if (conversation != null) {
            List<Conversation> loadedConversations = conversationPort.getAllConversationsByParticipants(conversation.getParticipants());

            if (!conversation.getParticipants().isEmpty()) {

                for (String participant : conversation.getParticipants()) {
                    if (participant.contains(jidPostfix)) {
                        participant = participant.replace(jidPostfix, "");
                    }
                    List<UserRepresentation> result = keycloakAdminClientTemp.findByUsername(participant, SecurityConfig.API_TOKEN);
                    if (result.size() == 0) {
                        throw new IllegalArgumentException("user does not have exist : " + conversation.getParticipants().get(0));
                    }
                }

                conversation.setParticipants(
                        conversation.getParticipants().stream()
                                .map(participant ->
                                        participant.endsWith(jidPostfix)
                                                ? participant
                                                : participant + jidPostfix
                                )
                                .collect(Collectors.toList())
                );
            }

            if (loadedConversations ==null || loadedConversations.isEmpty()) {
                conversationPort.save(conversation);
            } else {
                throw new RuntimeException("updating conversation already not implemented");
            }
        }
    }

}

