package io.projectZ.orchestrator.infrastructure.adapter.in.broker.userEvent;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/31/2026 - 3:16 PM
*/

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.AdminEventDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.EventDTO;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.UserEventDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EventDeserializer implements Deserializer<EventDTO> {

    private final ObjectMapper objectMapper;

    public EventDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public EventDTO deserialize(String topic, byte[] data) {

        System.out.println("deserializing ...");
        if (data == null || data.length == 0) {
            return null;
        }

        try {
            JsonNode node = objectMapper.readTree(data);

            boolean isUserEvent =node.get("userEvent").asBoolean();

            if (isUserEvent) {
                return objectMapper.treeToValue(node,UserEventDto.class);
            }

            return objectMapper.treeToValue(node,AdminEventDto.class);

        } catch (Exception e) {
            throw new SerializationException(
                    "Failed to deserialize EventDTO from topic: " + topic,
                    e
            );
        }
    }
}

