package io.projectZ.orchestrator.infrastructure.config;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/18/2026 - 2:53 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.in.xmpp.XmppClientListener;
import io.projectZ.orchestrator.infrastructure.adapter.out.keycloak.KeycloakTokenGateway;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational.ChatBotCacheDTO;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational.ChatBotCacheGateway;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.AiRestClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationInitializer {

    @Value("${orchestrator.mode}")
    private String mode;

    private Logger logger = LogManager.getLogger(ApplicationInitializer.class);

    @Autowired
    private XmppConnection xmppConnection;
    @Autowired
    private XmppClientListener xmppClientListener;
    @Autowired
    KeycloakTokenGateway<String> keycloakTokenGateway;
    @Autowired
    private AiRestClient aiRestClient;
    @Autowired
    private ChatBotCacheGateway chatBotCacheGateway;

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        String token = null;
        if (mode.equals("production")) {
            logger.info("mode production initializing ...");
            List<ChatBotCacheDTO> chatBotCacheDTOList = chatBotCacheGateway.list();
            for (ChatBotCacheDTO chatBotCacheDTO : chatBotCacheDTOList) {
                if (chatBotCacheDTO.isHasListener() || chatBotCacheDTO.isEstablished()) {
                    token = keycloakTokenGateway.retrieveToken(chatBotCacheDTO.getBotId());
                }
                if (chatBotCacheDTO.isEstablished()) {
                    try {
                        logger.info("openfire connection establishment for{}", chatBotCacheDTO.getBotId());
                        xmppConnection.connection(chatBotCacheDTO.getBotId(), token);
                    } catch (Exception exception) {
                        logger.error("establishment connection for {} caused error ", chatBotCacheDTO.getBotId());
                        chatBotCacheGateway.remove(chatBotCacheDTO.getBotId());
                    }
                }
                if (chatBotCacheDTO.isHasListener()) {

                    AbstractXMPPConnection connection = XmppConnection.getConnection(chatBotCacheDTO.getBotId());
                    if (connection != null)
                        try {
                            logger.info("adding listener for{}", chatBotCacheDTO.getBotId());
                            xmppClientListener.addListener(connection);
                        } catch (Exception exception) {
                            logger.error("adding listener for {} caused error ", chatBotCacheDTO.getBotId());
                            chatBotCacheGateway.listenerUnassigned(chatBotCacheDTO.getBotId());
                        }
                }
            }
        } else {
            logger.info("mode develop initializing ...");
        }
    }
}

