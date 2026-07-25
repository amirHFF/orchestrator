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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class ChatBotServiceImpl implements ChatBotService {
    private final Logger logger = LogManager.getLogger(ChatBotServiceImpl.class);
    private final ChatBotPersistencePort persistencePort;
    private final UserManagementPort userManagementPort;

    public ChatBotServiceImpl(ChatBotPersistencePort persistencePort, UserManagementPort userManagementPort) {
        this.persistencePort = persistencePort;
        this.userManagementPort = userManagementPort;
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
}