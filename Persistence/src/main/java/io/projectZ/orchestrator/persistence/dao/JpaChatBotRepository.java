package io.projectZ.orchestrator.persistence.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 6:02 PM
*/

import io.projectZ.orchestrator.persistence.entity.ChatBotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaChatBotRepository extends JpaRepository<ChatBotEntity , Long> {
    ChatBotEntity findByBotID(String botID);
}

