package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 10:40 AM
*/

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChatBot {
    private String keycloakId;
    private String BotID;
    @NotNull(message = "name is mandatory for chatBot")
    private String name;
    private String displayName;
    private String aiModel;
    private String promptCode;
    private String ownerUserId;
    private String creatorUserId;
    private boolean enabled = false;
    private String description;
    private String scope;
}

