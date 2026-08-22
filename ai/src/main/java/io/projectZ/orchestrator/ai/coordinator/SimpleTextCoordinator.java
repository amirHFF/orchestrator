package io.projectZ.orchestrator.ai.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:17 AM
*/

import io.projectZ.orchestrator.ai.config.AiLLMConfig;
import io.projectZ.orchestrator.ai.model.AiProxyModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleTextCoordinator implements AiCoordinator<String> {

    private ChatClient chatClient;
    private final ModelFactory modelFactory;
    private final PromptEngine promptEngine;

    public SimpleTextCoordinator(ModelFactory modelFactory, PromptEngine promptEngine) {
        this.modelFactory = modelFactory;
        this.promptEngine = promptEngine;
    }
    @Override
    public String processMessage(AiProxyModel proxy, String message) {

        chatClient = modelFactory.createChatClient(proxy.getModel());
        Prompt prompt = new Prompt(List.of(new SystemMessage("You are a helpful teacher. " +
                "you teach english , but talk in farsi ,your main purpose is ensuring good learning .")));

        if (message.length() > 10)
            prompt = promptEngine.render(proxy.getPrompt(), null);


        String response = chatClient.prompt()
                .system(prompt.getSystemMessage().getText())
                .user(message)
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, proxy.getRealUsername()))
                .call()
                .content();
        return response;
    }
}

