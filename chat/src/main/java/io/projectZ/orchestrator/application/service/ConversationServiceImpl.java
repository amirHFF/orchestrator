package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:02 PM
*/

import io.projectZ.orchestrator.application.port.ConversationPort;
import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.KeycloakAdminClient;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.UserRepresentation;
import io.projectZ.orchestrator.infrastructure.config.SecurityConfig;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ConversationServiceImpl implements ConversationService {
    private final static String jidPostfix = "@zchat.ir";
    private final ConversationPort conversationPort;
    private final KeycloakAdminClient keycloakAdminClient;

    public ConversationServiceImpl(ConversationPort conversationPort, KeycloakAdminClient keycloakAdminClient) {
        this.conversationPort = conversationPort;
        this.keycloakAdminClient = keycloakAdminClient;
    }

    @Override
    public List<Conversation> getAllConversationsByJid(String jid) {
        if (jid == null) {
            throw new IllegalArgumentException("jid is null");
        }
        if (!jid.contains(jidPostfix)) {
            jid = jid.concat(jidPostfix);
        }

        List<Conversation> conversations = conversationPort.getConversations(jid, jid);
        conversations.sort(Comparator.comparing(Conversation::getLastMessageTime));
        return conversations;
    }

    @Override
    public void saveOrUpdate(Conversation conversation) {
        if (conversation != null) {
            if (!conversation.getParticipants().isEmpty()) {

                for (String participant : conversation.getParticipants()) {
                    List<UserRepresentation> result = keycloakAdminClient.findByUsername(participant, SecurityConfig.API_TOKEN);
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
        }
        Conversation loadedConversation = conversationPort.getById(conversation.getId());
        if (loadedConversation == null) {
            conversationPort.save(conversation);
        } else if (!loadedConversation.getLastMessage().equals(conversation.getLastMessage())) {
            loadedConversation.setLastMessage(conversation.getLastMessage());
            conversationPort.update(loadedConversation);
        }
    }
}

