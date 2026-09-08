package io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto;
/*
    Project : keycloak-kafka-Listener
    Author  : a.FouladiFar
    Created : 08/07/2026
*/

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileEventDto extends ProfileEventDto {
    private String userid;
    private ProfileEventType profileEventType;
    private String clientId;

    public ProfileEventType getEventType() {
        return profileEventType;
    }

    public void setEventType(ProfileEventType profileEventType) {
        this.profileEventType = profileEventType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
