package io.projectZ.orchestrator.ai.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/29/2026 - 11:56 PM
*/

import io.projectZ.orchestrator.ai.DBRepository.AiModelRepository;
import io.projectZ.orchestrator.ai.memory.ChatMemoryService;
import io.projectZ.orchestrator.ai.model.AIModel;
import io.projectZ.orchestrator.ai.service.AiModelService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ModelFactory {

    @Autowired
    private AiModelService aiModelService;
    @Autowired
    private ChatMemoryService chatMemoryService;

    private final Map<String, ChatClient> clientCache = new ConcurrentHashMap<>();

    public ChatClient createChatClient(@Valid AIModel aiModel) {
        ChatClient chatClient = clientCache.get(aiModel.getName());
        if (chatClient != null) {
            return chatClient;
        }
        switch (aiModel.getType()) {
            case OPEN_AI -> {
                chatClient = buildOpenAIClient(aiModel);
                clientCache.put(aiModel.getName(), chatClient);
            }
        }
        return chatClient;
    }

    private ChatClient buildOpenAIClient(AIModel aiModel) {
        OpenAiApi openAiApi = OpenAiApi.builder().apiKey(aiModel.getApiKey()).baseUrl(aiModel.getUrl()).build();

        OpenAiChatModel model = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(
                        OpenAiChatOptions.builder()
                                .model(aiModel.getName())
                                .temperature(aiModel.getTemperature())
                                .build())
                .build();

        return ChatClient.builder(model).defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemoryService.createNewMemory()).build()).build();
    }

    public ChatClient updateModel(String modelName, @Valid AIModel aiModel) {
        clientCache.remove(modelName);
        return createChatClient(aiModel);
    }
}

