package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 5:53 PM
*/

import io.projectZ.orchestrator.application.port.ChatBotPersistencePort;
import io.projectZ.orchestrator.persistence.dao.JpaChatBotRepository;
import io.projectZ.orchestrator.entity.ChatBot;
import io.projectZ.orchestrator.persistence.entity.ChatBotEntity;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.ChatBotMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChatBotRepository implements ChatBotPersistencePort {

    private final JpaChatBotRepository jpaChatBotRepository;

    public ChatBotRepository(JpaChatBotRepository jpaChatBotRepository) {
        this.jpaChatBotRepository = jpaChatBotRepository;
    }

    @Override
    public ChatBot getByBotID(String botID) {
        ChatBot chatBot = null;
        if (botID != null) {
            ChatBotEntity chatBotEntity = jpaChatBotRepository.findByBotID(botID);
            chatBot = ChatBotMapper.getInstance.entityToModel(chatBotEntity);
        }
        return chatBot;
    }

    @Override
    public List<ChatBot> getAll(Boolean enabled) {
        List<ChatBotEntity> entityBotList;
        if (enabled == null){
            entityBotList = jpaChatBotRepository.findAll(Sort.by(Sort.Order.desc("insertTime")));
        }
        else {
            entityBotList = jpaChatBotRepository.findAllByEnabledOrderByInsertTimeDesc(enabled);
        }
        return entityBotList.stream().map(ChatBotMapper.getInstance::entityToModel).collect(Collectors.toList());
    }

    @Override
    public void save(ChatBot chatBot) {
        ChatBotEntity chatBotEntity = ChatBotMapper.getInstance.modelToEntity(chatBot);
        jpaChatBotRepository.save(chatBotEntity);
    }

    @Override
    public void update(ChatBot chatBot) {
        if (chatBot.getBotID() != null) {
            ChatBotEntity loadedChatBot = jpaChatBotRepository.findByBotID(chatBot.getBotID());
            if (loadedChatBot !=null){
                loadedChatBot.setName(chatBot.getName());
                loadedChatBot.setEnabled(chatBot.isEnabled());
                loadedChatBot.setPromptCode(chatBot.getPromptCode());
                loadedChatBot.setScope(chatBot.getScope());
                loadedChatBot.setDescription(chatBot.getDescription());
                jpaChatBotRepository.save(loadedChatBot);
            }else {
                throw new RuntimeException("chatbot does not find : " + chatBot.getBotID());
            }
        }
        else {
            throw new IllegalArgumentException("bot id is empty");
        }
    }
}

