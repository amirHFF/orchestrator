package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/15/2026 - 6:33 PM
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ChatBotCacheDTO {
    private String botId;
    private boolean isEstablished = false;
    private boolean hasListener = false;
    private long lastUpdate;

    public ChatBotCacheDTO(String botId, boolean isEstablished, boolean hasListener) {
        this.botId = botId;
        this.isEstablished = isEstablished;
        this.hasListener = hasListener;
        this.lastUpdate = System.currentTimeMillis();
    }

    public ChatBotCacheDTO() {
        this.lastUpdate = System.currentTimeMillis();
    }
}

