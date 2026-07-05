package io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.request;
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
public class ConversationRequest {
    private String jid;
    private String targetJid;
    private String lastMessage;
}

