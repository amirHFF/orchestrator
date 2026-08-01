package io.projectZ.orchestrator.application.port;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 8:07 PM
*/

import io.projectZ.orchestrator.entity.AiChatTalk;
import io.projectZ.orchestrator.infrastructure.adapter.out.ai.dto.ChatTalk;

public interface AiPort {
    AiChatTalk ask(ChatTalk chatTalk);
}
