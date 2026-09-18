package io.projectZ.orchestrator.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/15/2026 - 8:52 PM
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatBotStatus {

    private String botId;
    private boolean isEstablished;
    private boolean hasListener;
    private long lastUpdate;
    public ChatBotStatus() {
        lastUpdate = System.currentTimeMillis();
    }
}

