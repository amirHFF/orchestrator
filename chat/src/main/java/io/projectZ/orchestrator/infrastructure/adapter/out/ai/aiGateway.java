package io.projectZ.orchestrator.infrastructure.adapter.out.ai;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 9:55 PM
*/

import io.projectZ.orchestrator.application.port.AiPort;
import io.projectZ.orchestrator.entity.AiChatTalk;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.AiRestClient;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.AiResponse;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.ChatTalkRequest;
import io.projectZ.orchestrator.infrastructure.config.ChatRequestFilter;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class aiGateway implements AiPort {
    private final AiRestClient restClient;
    public aiGateway(AiRestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public AiChatTalk ask(String botID,String message , String userId) {
        AiChatTalk chatTalk = null;

        ChatTalkRequest chatTalkRequest = new ChatTalkRequest(userId,botID , message);
        AiResponse aiResponse = restClient.sendQuestion(chatTalkRequest);
        if (aiResponse != null) {
            chatTalk = new AiChatTalk();
            chatTalk.setContent(aiResponse.getContent());
        }
        return chatTalk;
    }
}

