package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:36 PM
*/

 import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "conversation")
public class ConversationEntity extends BaseEntity {
    @Id
    @SequenceGenerator(name = "conversationSeq" , sequenceName = "CONVERSATION_SEQ" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "conversationSeq")
    private long id;
    @Column(columnDefinition = "text[]")
    private List<String> participants = new ArrayList<>();
    @Column(name = "LAST_MESSAGE" )
    private String lastMessage;

}

