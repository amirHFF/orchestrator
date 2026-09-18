package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 5:56 PM
*/

import io.projectZ.orchestrator.persistence.relational.entity.ConversationTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Conversation{

  private Long id;
  private String title;
  private List<String> participants;
  private String lastMessage;
  private ConversationTypeEnum conversationType;
  private LocalDateTime lastMessageTime;

}

