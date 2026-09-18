package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:02 PM
*/

import io.github.amirHFF.exceptions.DuplicateException;
import io.github.amirHFF.exceptions.NotFoundException;
import io.projectZ.orchestrator.application.port.ConversationPort;
import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.KeycloakAdminClientTemp;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.UserRepresentation;
import io.projectZ.orchestrator.infrastructure.config.SecurityConfig;
import io.projectZ.orchestrator.infrastructure.config.advice.ChatErrorCode;
import io.projectZ.orchestrator.persistence.relational.entity.ConversationTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;


@Service
public class ConversationServiceImpl implements ConversationService {

    private final Logger logger = LogManager.getLogger(ConversationServiceImpl.class);

    private final ConversationPort conversationPort;
    private final KeycloakAdminClientTemp keycloakAdminClientTemp;
    private final ProfileService profileService;

    public ConversationServiceImpl(ConversationPort conversationPort, KeycloakAdminClientTemp keycloakAdminClientTemp, ProfileService profileService) {
        this.conversationPort = conversationPort;
        this.keycloakAdminClientTemp = keycloakAdminClientTemp;
        this.profileService = profileService;
    }

    @Override
    public List<Conversation> getAllConversationsByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("jid is null");
        }

        List<Conversation> conversations = conversationPort.getAllConversationsByUsername(username);
        conversations.sort(Comparator.comparing(Conversation::getLastMessageTime));
        return conversations;
    }

    @Override
    @Transactional
    public Conversation save(Conversation conversation) {
        if (conversation == null) {
            throw new IllegalArgumentException("conversation argumant is null");
        }
        long start = System.currentTimeMillis();
        List<Conversation> loadedConversations = conversationPort.getAllConversationsByParticipants(conversation.getParticipants());
        long end = System.currentTimeMillis();
        System.out.println("executionTim = " + (end - start));
        if (loadedConversations != null && !loadedConversations.isEmpty()) {
            throw new DuplicateException(ChatErrorCode.DUPLICATE_CONVERSATION);
        }

        if (conversation.getParticipants().isEmpty()) {
            throw new IllegalArgumentException("participants is empty");
        }
        String currentUsername = SecurityConfig.getCurrentUsername();
        for (String participant : conversation.getParticipants()) {
            if (!currentUsername.equals(participant)) {
                List<UserRepresentation> result = keycloakAdminClientTemp.findByUsername(participant, SecurityConfig.API_TOKEN);
                if (result == null || result.isEmpty()) {
                    throw new RuntimeException("");
                }
                UserRepresentation user = result.getFirst();
                if (conversation.getConversationType().equals(ConversationTypeEnum.CHAT)) {
                    if (user.getFirstName() != null) {

                        conversation.setTitle(user.getFirstName().concat(" ").concat((user.getLastName().equals("BOT")) ? "" : user.getLastName()));
                    } else {
                        conversation.setTitle(user.getUsername());
                    }
                }
                if (result.size() == 0) {
                    logger.error("username does not have exist for adding as a participant , username {}", participant);
                    throw new NotFoundException(ChatErrorCode.USER_NAME_NOT_FOUND);
                }
            }
        }

        logger.info("conversation is saving ...");
        if (conversation.getParticipants().size() == 2) {
            conversation.setConversationType(ConversationTypeEnum.CHAT);
        }
        conversationPort.save(conversation);
        return conversation;

    }

    @Override
    public void update(Conversation conversation) {

    }

}

