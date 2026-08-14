package io.projectZ.orchestrator.infrastructure.adapter.out.restClient;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 11:30 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.AiResponse;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.ChatTalkRequest;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AiRestClient {

    @Value("${restClient.ai.url}")
    private String url;

    private Logger logger = LogManager.getLogger(AiRestClient.class);
    public RestClient restClient;

    @PostConstruct
    public void init() {
        restClient = RestClient.builder().baseUrl(url).build();
    }

    public AiResponse sendQuestion(ChatTalkRequest chatTalkRequest) {
        AiResponse aiResponse = null;
        try {
            aiResponse = restClient.post().uri("/ai/ask")
                    .header("accept", "application/json")
//                    .header("Authorization", "Bearer "+accessToken)
                    .body(chatTalkRequest)
                    .retrieve().body(AiResponse.class);
        } catch (Exception e) {
            logger.error("communication to ai server failed : ", e);
        }
        return aiResponse;
    }
}

