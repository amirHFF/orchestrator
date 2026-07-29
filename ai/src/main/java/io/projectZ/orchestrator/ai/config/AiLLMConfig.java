package io.projectZ.orchestrator.ai.config;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/17/2026 - 1:19 PM
*/

import io.projectZ.orchestrator.ai.memory.ChatMemoryService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class AiLLMConfig {
    public  Map<String , String> promptMap = new HashMap<>();
    private final ResourceLoader resourceLoader;
    private final ChatMemoryService chatMemoryService;

    public AiLLMConfig(ResourceLoader resourceLoader, ChatMemoryService chatMemoryService) {
        this.resourceLoader = resourceLoader;
        this.chatMemoryService = chatMemoryService;
        initializePrompts();
    }

    public  void  initializePrompts() {
        //todo in ghesmat movaghati hast va badan kollan pak mishe
        promptMap.put("drunk" , loadPersonaPrompt("clingy"));
        promptMap.put("Clingy" , loadPersonaPrompt("drunk"));
        promptMap.put("jerk" , loadPersonaPrompt("jerk"));
    }

    @Bean(name = "qwen")
    public ChatClient qwenChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultSystem("You are a helpful teacher. you teach english , but talk in farsi ,your main purpose is ensuring good learning ") // پرامپت پیش‌فرض
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemoryService.createNewMemory()).build())
                .build();
    }
    @Bean(name = "gpt")
    public ChatClient gptChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultSystem("You are a helpful teacher. you teach english , but talk in farsi ,your main purpose is ensuring good learning ") // پرامپت پیش‌فرض
                .build();
    }

    private String loadPersonaPrompt(String filename) {
        try {
            // ۱. لود کردن رفرنس فایل با پیشوند classpath
            Resource resource = resourceLoader.getResource("classpath:persona/"+filename+".txt");

            // ۲. باز کردن فایل به صورت Stream (امن برای فایل‌های داخل JAR)
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

                // ۳. تبدیل تمام خطوط فایل به یک String واحد
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (Exception e) {
            throw new RuntimeException("خطا در بارگذاری فایل محتوا: " + e.getMessage(), e);
        }
    }
}

