package io.projectZ.orchestrator.infrastructure.adapter.in.xmpp;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 10:39 AM
*/

import io.projectZ.orchestrator.application.service.ChatMessageHandler;
import io.projectZ.orchestrator.entity.ChatMessage;
import io.projectZ.orchestrator.infrastructure.config.XmppConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.springframework.stereotype.Service;

@Service
public class XmppClientListener {
    private final Logger logger = LogManager.getLogger(XmppClientListener.class);
    private final ChatMessageHandler chatMessageHandler;

    public XmppClientListener(ChatMessageHandler chatMessageHandler) {
        this.chatMessageHandler = chatMessageHandler;
    }

    public void addListener(String username){
        AbstractXMPPConnection connection = XmppConnection.getConnection(username);

        connection.addAsyncStanzaListener(stanza -> {
            logger.info("received message from: {} ", stanza.getFrom());
                chatMessageHandler.handleReceivedMessage(mapStanzaToChatMessage(stanza));
                },
                stanza ->
                        stanza instanceof Message
                                && ((Message) stanza).getBody() != null);
    }
    public void addListener(AbstractXMPPConnection connection) {
        if (connection != null && connection.isConnected()) {

            connection.addAsyncStanzaListener(stanza -> {
                        logger.info("received message from: {} ", stanza.getFrom());
                        chatMessageHandler.handleReceivedMessage(mapStanzaToChatMessage(stanza));
                    },
                    stanza ->
                            stanza instanceof Message
                                    && ((Message) stanza).getBody() != null);
        }
    }

    private ChatMessage mapStanzaToChatMessage(Stanza stanza){
        Message message = (Message) stanza;
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setTo(message.getTo().getLocalpartOrThrow().toString());
        chatMessage.setFrom(message.getFrom().getLocalpartOrThrow().toString());
        chatMessage.setContent(message.getBody());
        chatMessage.setMessageTime(System.currentTimeMillis());
        return chatMessage;
    }
}

