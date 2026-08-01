package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 8:11 PM
*/

import io.projectZ.orchestrator.application.port.AiPort;
import io.projectZ.orchestrator.application.port.ChatPort;
import io.projectZ.orchestrator.entity.AiChatTalk;
import io.projectZ.orchestrator.entity.ChatMessage;
import io.projectZ.orchestrator.infrastructure.adapter.out.ai.dto.ChatTalk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ChatBotMessageHandler implements ChatMessageHandler{
    private final Logger logger = LogManager.getLogger(ChatBotMessageHandler.class);
    private final AiPort aiPort;
    private final ChatPort chatPort;

    public ChatBotMessageHandler(AiPort aiPort, ChatPort chatPort) {
        this.aiPort = aiPort;
        this.chatPort = chatPort;
    }

    @Override
    public void handleReceivedMessage(ChatMessage chatMessage) {
        logger.info("messsage received to chat bot service id: {}",chatMessage.getId());
        AiChatTalk aiChatTalk = aiPort.ask(new ChatTalk(chatMessage.getTo() , chatMessage.getFrom() ,"qwen" , "template-test-1",chatMessage.getContent()));
        ChatMessage aiResponseChatMessage = new ChatMessage(aiChatTalk.getContent(), chatMessage.getTo() , chatMessage.getFrom());
        sendMessage(aiResponseChatMessage);
    }

    @Override
    public void sendMessage(ChatMessage message) {
        logger.info("messsage send to:{}",message.getTo());
        chatPort.sendMessage(message);
    }
}

