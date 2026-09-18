package io.projectZ.orchestrator.persistence.unrelational.redis.entity;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/15/2026 - 12:19 AM
*/

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter

public class ChatBotStatus implements Serializable {

    private String botId;
    private boolean isEstablished = false;
    private boolean hasListener = false;
    private long lastUpdate;

    public ChatBotStatus() {
        lastUpdate = System.currentTimeMillis();
    }

    public ChatBotStatus(String botId) {
        this.botId = botId;
        lastUpdate = System.currentTimeMillis();
    }
}

