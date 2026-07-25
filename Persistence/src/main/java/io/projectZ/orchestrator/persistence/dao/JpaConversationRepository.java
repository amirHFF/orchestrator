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

    @Query(value = """
    SELECT *
    FROM conversation
    WHERE :participant = ANY(participants)
    """, nativeQuery = true)    List<ConversationEntity> findAllConversationsBySingleParticipant(@Param("participant") String participant);
    @Query(value = "select * from Conversation WHERE participants @> CAST(:participants AS text[])",nativeQuery = true)
    List<ConversationEntity> findAllConversationsByAllParticipant(List<String> participants);

}

