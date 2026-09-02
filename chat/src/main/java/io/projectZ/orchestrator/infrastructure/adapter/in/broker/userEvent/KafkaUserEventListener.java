package io.projectZ.orchestrator.infrastructure.adapter.in.broker.userEvent;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/31/2026 - 1:53 AM
*/

import io.projectZ.orchestrator.application.service.ProfileService;
import io.projectZ.orchestrator.entity.ChatProfile;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.EventHandler;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.EventDTO;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.UserEventDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaUserEventListener {

    private EventHandler handler;

    public KafkaUserEventListener(EventHandler handler) {
        this.handler = handler;
    }

    private final Logger logger = LogManager.getLogger(KafkaUserEventListener.class);

    @KafkaListener(topics = "user-sync-events", groupId = "orch-core")
    public void consume(EventDTO eventDTO, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.OFFSET) long offset) {
        logger.info("Event received from partition {} offset {} -> {}", partition, offset, eventDTO.toString());

        if (eventDTO != null) {
            if (eventDTO instanceof UserEventDto userEventDto) {
                handler.handle(userEventDto);
            }
        }
    }
}

