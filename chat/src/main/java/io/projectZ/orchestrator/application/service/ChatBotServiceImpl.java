package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 11:57 AM
*/

import io.github.amirHFF.exceptions.NotFoundException;
import io.projectZ.orchestrator.application.port.ChatBotPersistencePort;
import io.projectZ.orchestrator.application.port.UserManagementPort;
import io.projectZ.orchestrator.application.service.internalProcess.BotIDGenerator.BotIdentifierGenerator;
import io.projectZ.orchestrator.entity.BotUser;
import io.projectZ.orchestrator.entity.ChatBot;
import io.projectZ.orchestrator.entity.ChatBotStatus;
import io.projectZ.orchestrator.infrastructure.adapter.in.xmpp.XmppClientListener;
import io.projectZ.orchestrator.infrastructure.adapter.out.keycloak.KeycloakTokenGateway;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational.ChatBotCacheDTO;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational.ChatBotCacheGateway;
import io.projectZ.orchestrator.infrastructure.config.XmppConnection;
import io.projectZ.orchestrator.infrastructure.config.advice.ChatErrorCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
public class ChatBotServiceImpl implements ChatBotService {
    private final Logger logger = LogManager.getLogger(ChatBotServiceImpl.class);
    private final ChatBotPersistencePort persistencePort;
    private final UserManagementPort userManagementPort;
    private final KeycloakTokenGateway<String> keycloakTokenGateway;
    private final XmppConnection xmppConnection;
    private final XmppClientListener xmppClientListener;
    private final ChatBotCacheGateway cacheGateway;

    public ChatBotServiceImpl(ChatBotPersistencePort persistencePort,
                              UserManagementPort userManagementPort,
                              KeycloakTokenGateway<String> keycloakTokenGateway,
                              XmppConnection xmppConnection,
                              XmppClientListener xmppClientListener,
                              ChatBotCacheGateway cacheGateway) {
        this.persistencePort = persistencePort;
        this.userManagementPort = userManagementPort;
        this.keycloakTokenGateway = keycloakTokenGateway;
        this.xmppConnection = xmppConnection;
        this.xmppClientListener = xmppClientListener;
        this.cacheGateway = cacheGateway;
    }

    @Override
    public ChatBot get(String BotID) {
        if (BotID != null) {
            return persistencePort.getByBotID(BotID);
        } else throw new IllegalArgumentException("bot id is empty");
    }

    @Override
    public ChatBotStatus getStatus(String BotID) {
        ChatBotStatus chatBotStatus = null;
        ChatBotCacheDTO cacheDTO = cacheGateway.get(BotID);
        if (cacheDTO != null) {
            chatBotStatus = new ChatBotStatus();
            chatBotStatus.setEstablished(cacheDTO.isEstablished());
            chatBotStatus.setBotId(cacheDTO.getBotId());
            chatBotStatus.setHasListener(cacheDTO.isHasListener());
            return chatBotStatus;
        } else {
            throw new NotFoundException(ChatErrorCode.BOT_DOES_NOT_FOUND);
        }
    }

    @Override
    public List<ChatBot> getAll(Boolean enabled) {
        return persistencePort.getAll(enabled);
    }

    @Override
    @Transactional
    public ChatBot save(@Valid ChatBot chatBot) {
        String BotID = BotIdentifierGenerator.generateRaw();
        chatBot.setBotID(BotID);
        String keycloakId = userManagementPort.registerUser(createBotUser(chatBot));
        chatBot.setKeycloakId(keycloakId);
        persistencePort.save(chatBot);
        return chatBot;
    }

    private BotUser createBotUser(ChatBot chatBot) {
        BotUser botUser = new BotUser();
        botUser.setUsername(chatBot.getBotID());
        botUser.setFirstname(chatBot.getDisplayName());
        botUser.setLastname("BOT");
        botUser.setPassword("!23");
        botUser.setEmail(botUser.getUsername().concat(".simorq@gmail.com"));
        return botUser;
    }

    @Override
    public ChatBot update(ChatBot chatBot) {
        persistencePort.update(chatBot);
        return chatBot;
    }

    @Override
    public void start(String botID) {
        ChatBot chatBot = get(botID);
        String token = keycloakTokenGateway.retrieveToken(chatBot.getBotID());
        AbstractXMPPConnection connection = xmppConnection.connection(botID, token);
        xmppClientListener.addListener(connection);
    }

    @Override
    public void stop(String BotID) {
        xmppConnection.closeConnection(BotID);
    }
}