package io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.response;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 10:40 AM
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatBotStatusResponseDto {
    private String botId;
    private boolean hasListener;
    private boolean established;
}

