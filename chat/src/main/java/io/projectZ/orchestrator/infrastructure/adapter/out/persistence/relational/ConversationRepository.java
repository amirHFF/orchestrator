package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:22 PM
*/

import io.github.amirHFF.exceptions.NotFoundException;
import io.projectZ.orchestrator.application.port.ConversationPort;
import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.ConversationMapper;
import io.projectZ.orchestrator.infrastructure.config.advice.ChatErrorCode;
import io.projectZ.orchestrator.persistence.dao.JpaChatProfileRepository;
import io.projectZ.orchestrator.persistence.dao.JpaConversationRepository;
import io.projectZ.orchestrator.persistence.entity.ChatProfileEntity;
import io.projectZ.orchestrator.persistence.entity.ConversationEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ConversationRepository implements ConversationPort {
    private final Logger logger = LogManager.getLogger(ConversationMapper.class);
    private final JpaConversationRepository repository;
    private final JpaChatProfileRepository chatProfileRepository;

    public ConversationRepository(JpaConversationRepository repository, JpaChatProfileRepository chatProfileRepository) {
        this.repository = repository;
        this.chatProfileRepository = chatProfileRepository;
    }

    @Override
    public Conversation getById(long id) {
        ConversationEntity conversationEntity = repository.findById(id).orElse(null);
        if (conversationEntity != null)
            return ConversationMapper.getInstance.entityToModel(conversationEntity);
        else return null;
    }

    @Override
    public void save(Conversation conversation) {
        logger.info("conversation saving ...");
        ConversationEntity entity = ConversationMapper.getInstance.modelToEntity(conversation);
        for (String participant : conversation.getParticipants()) {
            ChatProfileEntity chatProfileEntity = chatProfileRepository.findByUsername(participant);
            if (chatProfileEntity == null){
                logger.error("user name does not found in db {}",participant);
                throw new NotFoundException(ChatErrorCode.USER_NAME_NOT_FOUND);
            }
            entity.getParticipants().add(chatProfileEntity);
        }
        repository.save(entity);
        logger.info("conversation saved .");

    }

    @Transactional
    @Override
    public void update(Conversation conversation) {
        logger.info(" conversation updating ...");
        if (conversation.getId() != null) {
            ConversationEntity loadedConversation = repository.findById(conversation.getId()).orElse(null);
            if (loadedConversation == null)
                throw new RuntimeException("conversation does not found : " + conversation.getId());

        }
        else {
            repository.findByParticipants(conversation.getParticipants());
        }
        ConversationEntity entity = ConversationMapper.getInstance.modelToEntity(conversation);
        logger.info("conversation updating .");

    }

    @Override
    public List<Conversation> getAllConversationsByUsername(String jid) {
        List<ConversationEntity> dbResult = repository.findAllByUsername(jid);
        if (dbResult.isEmpty()) {
            return new ArrayList<>();
        }
        logger.info("{} in total fetched", dbResult.size());
        return dbResult.stream().map(ConversationMapper.getInstance::entityToModel).collect(Collectors.toList());
    }

    @Override
    public List<Conversation> getAllConversationsByParticipants(List<String> participants) {
        List<ConversationEntity> dbResult = repository.findByParticipants(participants);
        if (dbResult.isEmpty()) {
            return new ArrayList<>();
        }
        logger.info("{} in total fetched", dbResult.size());
        return dbResult.stream().map(ConversationMapper.getInstance::entityToModel).collect(Collectors.toList());
    }
}
