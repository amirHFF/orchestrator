package io.projectZ.orchestrator.persistence.relational.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/20/2026 - 12:27 AM
*/

import io.projectZ.orchestrator.persistence.relational.entity.ChatProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaChatProfileRepository extends JpaRepository<ChatProfileEntity, Long> {
    ChatProfileEntity findByUsername(String username);
}
