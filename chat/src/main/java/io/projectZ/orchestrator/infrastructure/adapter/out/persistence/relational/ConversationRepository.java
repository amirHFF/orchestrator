package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:22 PM
*/

import io.projectZ.orchestrator.application.port.ConversationPort;
import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.ConversationMapper;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.dao.JpaConversationRepository;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.model.ConversationEntity;
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

    public ConversationRepository(JpaConversationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Conversation getById(long id) {
        ConversationEntity conversationEntity = repository.findById(id).orElse(null);
        if (conversationEntity !=null)
            return ConversationMapper.getInstance.entityToModel(conversationEntity);
        else return null;
    }

    @Override
    public void save(Conversation conversation) {
        logger.info("conversation saving ...");
        ConversationEntity entity = ConversationMapper.getInstance.modelToEntity(conversation);
        repository.save(entity);
        logger.info("conversation saved .");

    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void update(Conversation conversation) {
        logger.info(" conversation updating ...");
        ConversationEntity entity = ConversationMapper.getInstance.modelToEntity(conversation);
        repository.save(entity);
        logger.info("conversation updating .");

    }

    @Override
    public List<Conversation> getConversations(String jid , String targetJid) {
        List<ConversationEntity> dbResult = repository.findAllConversationsBySingleParticipant(jid);
        if (dbResult.isEmpty()){
            return new ArrayList<>();
        }
        logger.info("{} in total fetched" , dbResult.size());
        return dbResult.stream().map(ConversationMapper.getInstance::entityToModel).collect(Collectors.toList());
    }

    @Override
    public List<Conversation> getConversationByParticipants(List<String> participants) {
        List<ConversationEntity> dbResult = repository.findAllConversationsByAllParticipant(participants);
        if (dbResult.isEmpty()){
            return new ArrayList<>();
        }
        logger.info("{} in total fetched" , dbResult.size());
        return dbResult.stream().map(ConversationMapper.getInstance::entityToModel).collect(Collectors.toList());
    }
}
