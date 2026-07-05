package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 5:56 PM
*/

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;

@Getter
@Setter
public class Conversation{

  private long id;
  private String jid;
  private String targetJid;

  private String lastMessage;
  private LocalDateTime lastMessageTime;



}

