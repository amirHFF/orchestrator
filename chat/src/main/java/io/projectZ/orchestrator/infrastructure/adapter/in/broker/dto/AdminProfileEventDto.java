package io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto;
/*
    Project : keycloak-kafka-Listener
    Author  : a.FouladiFar
    Created : 08/07/2026
*/


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.userEvent.StringifiedJsonDeserializer;
import io.projectZ.orchestrator.userManagement.dto.UserRepresentation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminProfileEventDto extends ProfileEventDto {

    private String resourceType;
    private String resourceId;
    private String operationType;
    private AuthDetails authDetails;
    @JsonDeserialize(using = StringifiedJsonDeserializer.class)
    private UserRepresentation representation;
}
