package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:35 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.model.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface JpaConversationRepository extends JpaRepository<ConversationEntity , Long> {

    List<ConversationEntity> findAllByJid(String jid);
    ConversationEntity findByJidAndTargetJid(String jid , String targetJid);

}

