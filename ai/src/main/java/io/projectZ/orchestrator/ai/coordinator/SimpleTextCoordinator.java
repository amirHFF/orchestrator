package io.projectZ.orchestrator.ai.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:17 AM
*/

import io.projectZ.orchestrator.ai.config.AiLLMConfig;
import io.projectZ.orchestrator.ai.model.AiActorModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    public String processMessage(AiActorModel actor ,String message) {
        if (actor.getModel().equals("gpt"))
            chatClient = (ChatClient) appctx.getBean("gpt");
        else if (actor.getModel().equals("qwen"))
            chatClient = (ChatClient) appctx.getBean("qwen");

        String personaPrompt="";
        if (message.length() > 10)
            personaPrompt = aiLLMConfig.promptMap.get(actor.getModel().toString());

        String response = chatClient.prompt()
                .system(personaPrompt)
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID , actor.getUsername()))
                .call()
                .content();
        return response;
    }
}

