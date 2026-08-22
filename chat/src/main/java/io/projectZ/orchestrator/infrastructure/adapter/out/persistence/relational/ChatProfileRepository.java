package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/20/2026 - 1:06 AM
*/

import io.projectZ.orchestrator.application.port.ChatProfilePort;
import io.projectZ.orchestrator.entity.ChatProfile;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.ChatProfileMapper;
import io.projectZ.orchestrator.persistence.dao.JpaChatProfileRepository;
import io.projectZ.orchestrator.persistence.entity.ChatProfileEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ChatProfileRepository implements ChatProfilePort {

    private final JpaChatProfileRepository repository;

    public ChatProfileRepository(JpaChatProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public ChatProfile getByUsername(String username) {
        ChatProfileEntity chatProfileEntity = repository.findByUsername(username);
        return ChatProfileMapper.getInstance.entityToModel(chatProfileEntity);
    }

    @Transactional
    @Override
    public void save(ChatProfile chatProfile) {
        repository.save(ChatProfileMapper.getInstance.modelToEntity(chatProfile));
    }

    @Override
    public void setDisplayName(String displayName , String username) {
        ChatProfileEntity  loadedProfile = repository.findByUsername(username);
        loadedProfile.setDisplayName(displayName);
        repository.save(loadedProfile);
    }

    @Override
    public List<ChatProfile> fetchAllUser() {
        return repository.findAll().stream().map(ChatProfileMapper.getInstance::entityToModel).collect(Collectors.toList());
    }
}

