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
public class ChatBotResponseDto {
    private String botID;
    private String name;
    private String displayName;
    private String ownerUserName;
    private String promptCode;
    private String aiModel;
    private String scope;
    private Boolean enabled;
    private String description;
}

