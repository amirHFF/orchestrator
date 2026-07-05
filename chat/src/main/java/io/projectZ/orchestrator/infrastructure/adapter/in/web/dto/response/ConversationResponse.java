package io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.response;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 7:03 PM
*/

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConversationResponse {
    private String jid;
    private String targetJid;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
}

