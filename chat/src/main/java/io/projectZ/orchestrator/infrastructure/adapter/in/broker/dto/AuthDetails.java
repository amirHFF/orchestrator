package io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/31/2026 - 2:16 AM
*/

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDetails {
    private String realmId;
    private String realmName;

    private String clientId;

    private String userId;

    private String ipAddress;

    public AuthDetails() {}
    public AuthDetails(AuthDetails toCopy) {
        this.realmId = toCopy.getRealmId();
        this.realmName = toCopy.getRealmName();
        this.clientId = toCopy.getClientId();
        this.userId = toCopy.getUserId();
        this.ipAddress = toCopy.getIpAddress();
    }
}

