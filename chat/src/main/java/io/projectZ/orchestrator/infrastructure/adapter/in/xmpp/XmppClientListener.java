package io.projectZ.orchestrator.infrastructure.adapter.in.xmpp;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 10:39 AM
*/

import io.projectZ.orchestrator.application.service.ChatMessageHandler;
import io.projectZ.orchestrator.entity.ChatMessage;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational.ChatBotCacheGateway;
import io.projectZ.orchestrator.infrastructure.config.XmppConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jxmpp.jid.EntityFullJid;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class XmppClientListener {
    private final Logger logger = LogManager.getLogger(XmppClientListener.class);
    private final ChatMessageHandler chatMessageHandler;
    private final ChatBotCacheGateway chatBotCacheGateway;

    public XmppClientListener(ChatMessageHandler chatMessageHandler, ChatBotCacheGateway chatBotCacheGateway) {
        this.chatMessageHandler = chatMessageHandler;
        this.chatBotCacheGateway = chatBotCacheGateway;
    }

    public void addListener(String username) {
        AbstractXMPPConnection connection = XmppConnection.getConnection(username);

        connection.addAsyncStanzaListener(stanza -> {
                    logger.info("received message from: {} ", stanza.getFrom());
                    chatMessageHandler.handleReceivedMessage(mapStanzaToChatMessage(stanza));
                },
                stanza ->
                        stanza instanceof Message
                                && ((Message) stanza).getBody() != null);
    }

    public synchronized void addListener(AbstractXMPPConnection connection) {
        Boolean listenerState =chatBotCacheGateway.getBotHasListener(connection.getUser().getLocalpartOrThrow().toString());

        if (listenerState ==null || !listenerState) {
            if (connection.isConnected()) {
                connection.addAsyncStanzaListener(stanza -> {
                            logger.info("received message from: {} ", stanza.getFrom());
                            chatMessageHandler.handleReceivedMessage(mapStanzaToChatMessage(stanza));
                        },
                        stanza ->
                                stanza instanceof Message
                                        && ((Message) stanza).getBody() != null);
                chatBotCacheGateway.listenerAssigned(connection.getUser().getLocalpartOrThrow().toString());


                connection.addAsyncStanzaListener(
                        this::handlePresence,
                        StanzaTypeFilter.PRESENCE
                );
            }
        }
    }



    private void handlePresence(Stanza stanza) {

        Presence presence = (Presence) stanza;

        EntityFullJid fullJid = presence.getFrom().asEntityFullJidIfPossible();

        if (fullJid == null) {
            return;
        }

        String username = fullJid.getLocalpartOrNull().toString();


        logger.debug("Presence received: {} -> {}", fullJid, presence.getType());

        if (presence.isAvailable()) {

            chatBotCacheGateway.listenerAssigned(username);

        } else if (presence.getType() == Presence.Type.unavailable) {

            chatBotCacheGateway.listenerUnassigned(username);

        }
    }


    private ChatMessage mapStanzaToChatMessage(Stanza stanza) {
        Message message = (Message) stanza;
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setTo(message.getTo().getLocalpartOrThrow().toString());
        chatMessage.setFrom(message.getFrom().getLocalpartOrThrow().toString());
        chatMessage.setContent(message.getBody());
        chatMessage.setMessageTime(System.currentTimeMillis());
        return chatMessage;
    }
}


