package io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/6/2026 - 9:46 PM
*/

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class EventDTO {
    private String consumerGroupIdentifier;

    public String getConsumerGroupIdentifier() {
        return consumerGroupIdentifier;
    }

    public void setConsumerGroupIdentifier(String consumerGroupIdentifier) {
        this.consumerGroupIdentifier = consumerGroupIdentifier;
    }
}

