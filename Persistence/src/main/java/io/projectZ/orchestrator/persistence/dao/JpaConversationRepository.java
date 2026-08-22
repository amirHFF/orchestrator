package io.projectZ.orchestrator.persistence.dao;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:35 PM
*/

import io.projectZ.orchestrator.persistence.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaConversationRepository extends JpaRepository<ConversationEntity , Long> {


    @Query(value = "select distinct e from ConversationEntity e join e.participants cp where cp.username = :username")
    List<ConversationEntity> findAllByUsername(String username);

    @Query("""
    SELECT c FROM ConversationEntity c
    WHERE c.conversationType = 'CHAT'
      AND (
          SELECT COUNT(DISTINCT p.username)
          FROM c.participants p
          WHERE p.username IN :participantParams
      ) = 2
    """)
    List<ConversationEntity> findByParticipants(@Param("participantParams") List<String> participantParams);

}

