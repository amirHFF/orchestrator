package io.projectZ.orchestrator.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:17 AM
*/

import io.projectZ.orchestrator.config.AiLLMConfig;
import io.projectZ.orchestrator.controller.dto.ChatTalkRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SimpleTextCoordinator implements AiCoordinator<String> {

    private ChatClient chatClient;

    @Autowired
    private ApplicationContext appctx;
    private final AiLLMConfig aiLLMConfig;

    public SimpleTextCoordinator(AiLLMConfig aiLLMConfig) {
        this.aiLLMConfig = aiLLMConfig;
    }

    //    public SimpleTextCoordinator(ChatClient.Builder chatClientBuilder) {
//        this.chatClientBuilder = chatClientBuilder;
//        chatClient = chatClientBuilder.defaultSystem("you are a english tutor and a bit drunk.txt , so your response have sometime dirty funny . your student are persian so sometime for better learning you must switch to persian ")
//                .build();
//    }
    @Override
    public String processMessage(ChatTalkRequest chatTalk , String sessionId) {
        if (chatTalk.getModel().equals("gpt"))
            chatClient = (ChatClient) appctx.getBean("gpt");
        else if (chatTalk.getModel().equals("qwen"))
            chatClient = (ChatClient) appctx.getBean("qwen");

        String personaPrompt="";
        if (chatTalk.getContent().length() > 10)
            personaPrompt = aiLLMConfig.promptMap.get(chatTalk.getPersona());

        String response = chatClient.prompt()
                .system(personaPrompt)
                .user(chatTalk.getContent())
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID , sessionId))
                .call()
                .content();
        return response;
    }
}

