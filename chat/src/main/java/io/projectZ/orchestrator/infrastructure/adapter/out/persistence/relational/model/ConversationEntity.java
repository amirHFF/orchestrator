package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.model;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:36 PM
*/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "conversation")
public class ConversationEntity extends BaseEntity {
    @Id
    @SequenceGenerator(name = "conversationSeq" , sequenceName = "CONVERSATION_SEQ" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "conversationSeq")
    private long id;
    private String jid;
    @Column(name = "TARGET_JID" , nullable = false , unique = true )
    private String targetJid;

    @Column(name = "LAST_MESSAGE" )
    private String lastMessage;

}

