package io.projectZ.orchestrator.persistence.relational.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 6:02 PM
*/

import io.projectZ.orchestrator.persistence.relational.entity.ChatBotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaChatBotRepository extends JpaRepository<ChatBotEntity, Long> {
    ChatBotEntity findByBotID(String botID);
    List<ChatBotEntity> findAllByEnabledOrderByInsertTimeDesc(boolean enable);
}

