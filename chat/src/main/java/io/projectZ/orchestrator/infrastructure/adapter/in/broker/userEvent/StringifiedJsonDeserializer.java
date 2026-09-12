package io.projectZ.orchestrator.infrastructure.adapter.in.broker.userEvent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.projectZ.orchestrator.userManagement.dto.UserRepresentation;

import java.io.IOException;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/12/2026 - 12:48 AM
*/
public class StringifiedJsonDeserializer extends JsonDeserializer<UserRepresentation> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserRepresentation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String rawJson = p.getValueAsString();

        if (rawJson == null || rawJson.isEmpty()) {
            return null;
        }

        return mapper.readValue(rawJson, UserRepresentation.class);
    }
}
