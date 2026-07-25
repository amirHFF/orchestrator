package io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.request;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 10:40 AM
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatBotRequestDto {
    private String name;
    private String ownerUserName;
    private String promptCode;
    private String scope;
    private Boolean isEnabled;
}

