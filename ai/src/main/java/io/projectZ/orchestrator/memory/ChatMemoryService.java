package io.projectZ.orchestrator.memory;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatMemoryService {

    private final Map<String, ChatMemory> sessionMemories = new ConcurrentHashMap<>();

    public ChatMemory getMemory(String sessionId) {
        return sessionMemories.get(sessionId);
    }

    public ChatMemory createNewMemory() {
        InMemoryChatMemoryRepository repository = new InMemoryChatMemoryRepository();

        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(repository)
                .maxMessages(20)           // حداکثر ۲۰ پیام
                .build();
    }

    /**
     * اضافه کردن پیام‌ها به حافظه - نسخه صحیح برای 1.1.8
     */
    public void addToMemory(String sessionId, String userMessageText, String assistantResponseText) {
        ChatMemory memory = getMemory(sessionId);

        // اضافه کردن پیام کاربر
        if (userMessageText != null && !userMessageText.isBlank()) {
            UserMessage userMessage = new UserMessage(userMessageText);
            memory.add(sessionId, userMessage);
        }

        // اضافه کردن پاسخ دستیار
        if (assistantResponseText != null && !assistantResponseText.isBlank()) {
            AssistantMessage assistantMessage = new AssistantMessage(assistantResponseText);
            memory.add(sessionId, assistantMessage);
        }
    }
}