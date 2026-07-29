package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 11:57 AM
*/

import io.projectZ.orchestrator.application.port.ChatBotPersistencePort;
import io.projectZ.orchestrator.application.port.UserManagementPort;
import io.projectZ.orchestrator.application.service.internalProcess.BotIDGenerator.BotIdentifierGenerator;
import io.projectZ.orchestrator.entity.BotUser;
import io.projectZ.orchestrator.entity.ChatBot;
import io.projectZ.orchestrator.infrastructure.adapter.in.xmpp.XmppClientListener;
import io.projectZ.orchestrator.infrastructure.adapter.out.keycloak.KeycloakTokenGateway;
import io.projectZ.orchestrator.infrastructure.config.XmppConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class ChatBotServiceImpl implements ChatBotService {
    private final Logger logger = LogManager.getLogger(ChatBotServiceImpl.class);
    private final ChatBotPersistencePort persistencePort;
    private final UserManagementPort userManagementPort;
    private final KeycloakTokenGateway<String> keycloakTokenGateway;
    private final XmppConnection xmppConnection;
    private final XmppClientListener xmppClientListener;

    public ChatBotServiceImpl(ChatBotPersistencePort persistencePort, UserManagementPort userManagementPort, KeycloakTokenGateway<String> keycloakTokenGateway, XmppConnection xmppConnection, XmppClientListener xmppClientListener) {
        this.persistencePort = persistencePort;
        this.userManagementPort = userManagementPort;
		this.keycloakTokenGateway = keycloakTokenGateway;
		this.xmppConnection = xmppConnection;
		this.xmppClientListener = xmppClientListener;
	}

    @Override
    public ChatBot get(String BotID) {
        if (BotID !=null) {
            return persistencePort.getByBotID(BotID);
        }
        else throw new IllegalArgumentException("bot id is empty");
    }

    @Override
    @Transactional
    public ChatBot save(@Valid  ChatBot chatBot) {
        String BotID = BotIdentifierGenerator.generateRaw();
        chatBot.setBotID(BotID);
        if (chatBot.getPromptCode()!=null){

        }
        String keycloakId= userManagementPort.registerUser(createBotUser(BotID));
        chatBot.setKeycloakId(keycloakId);
        persistencePort.save(chatBot);
        return chatBot;
    }
    private BotUser createBotUser(String botUsername){
        BotUser botUser = new BotUser();
        botUser.setUsername(botUsername);
        botUser.setFirstname(botUsername);
        botUser.setLastname("zchat");
        botUser.setPassword("!23");
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
        AbstractXMPPConnection connection = xmppConnection.connection(botID , token);
        xmppClientListener.addListener(connection);
    }
}