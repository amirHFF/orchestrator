package io.projectZ.orchestrator.infrastructure.adapter.out.restClient;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 11:30 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.AiResponse;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.ChatTalkRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AiRestClient {
    private Logger logger = LogManager.getLogger(AiRestClient.class);
    public RestClient restClient = RestClient.builder().baseUrl("http://localhost:8091").build();

    public AiResponse sendQuestion(ChatTalkRequest chatTalkRequest , String sessionId) {
        AiResponse aiResponse = null;
        try {
            aiResponse = restClient.post().uri("/chat/ai")
                    .header("accept", "application/json")
                    .header("sessionId", sessionId)
//                    .header("Authorization", "Bearer "+accessToken)
                    .body(chatTalkRequest)
                    .retrieve().body(AiResponse.class);
        } catch (Exception e) {
            logger.error("communication to ai server failed : ", e);
        }
        return aiResponse;
    }
}

