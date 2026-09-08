package io.projectZ.orchestrator.infrastructure.adapter.in.broker.userEvent;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/31/2026 - 1:53 AM
*/

import io.projectZ.orchestrator.infrastructure.adapter.in.broker.handler.EventHandler;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.AdminProfileEventDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.ProfileEventDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.UserProfileEventDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.handler.HandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaUserEventListener {

    private EventHandler userHandler;
    private EventHandler adminHandler;
    private HandlerContext handlerContext;

    public KafkaUserEventListener(@Qualifier("userEvent") EventHandler userHandler ,
                                  @Qualifier("adminEvent") EventHandler adminHandler) {
        this.userHandler = userHandler;
        this.adminHandler = adminHandler;
        handlerContext =new HandlerContext();
    }

    private final Logger logger = LogManager.getLogger(KafkaUserEventListener.class);

    @KafkaListener(topics = "user-sync-events", groupId = "orch-core")
    public void consume(ProfileEventDto profileEventDTO, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.OFFSET) long offset) {
        logger.info("Event received from partition {} offset {} -> {}", partition, offset, profileEventDTO.toString());

        if (profileEventDTO != null) {
            if (profileEventDTO instanceof UserProfileEventDto) {
                handlerContext.setStrategy(userHandler);
            }
            if (profileEventDTO instanceof AdminProfileEventDto) {
                handlerContext.setStrategy(adminHandler);
            }
            handlerContext.executeHandle(profileEventDTO);
        }

    }
}

