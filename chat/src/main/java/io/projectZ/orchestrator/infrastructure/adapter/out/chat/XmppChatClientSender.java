package io.projectZ.orchestrator.infrastructure.adapter.out.chat;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 10:03 PM
*/

import io.projectZ.orchestrator.application.port.ChatPort;
import io.projectZ.orchestrator.entity.ChatMessage;
import io.projectZ.orchestrator.infrastructure.adapter.in.xmpp.XmppClientListener;
import io.projectZ.orchestrator.infrastructure.config.XmppConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.MessageBuilder;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class XmppChatClientSender implements ChatPort {
    private Logger logger = LogManager.getLogger(XmppChatClientSender.class);
    @Override
    public void sendMessage(ChatMessage message) {
        AbstractXMPPConnection connection = XmppConnection.getConnection(message.getFrom());
        Message stanzaMessage = new Message();

        stanzaMessage.setType(Message.Type.chat);

        try {
            stanzaMessage.setTo(JidCreate.entityBareFrom(message.getTo().concat("@zchat.ir")));
        } catch (XmppStringprepException e) {
            throw new RuntimeException(e);
        }

        stanzaMessage.setBody(message.getContent());

        stanzaMessage.setStanzaId(UUID.randomUUID().toString());
        try {
            connection.sendStanza(stanzaMessage);
        } catch (SmackException.NotConnectedException e) {
            logger.error("sending message with xmpp protocol failed \n",e);
        } catch (InterruptedException e) {
            logger.error("sending message with xmpp protocol failed \n",e);
        }

    }
}

