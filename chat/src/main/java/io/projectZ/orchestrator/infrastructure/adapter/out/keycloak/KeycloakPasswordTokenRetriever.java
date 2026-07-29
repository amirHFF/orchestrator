package io.projectZ.orchestrator.infrastructure.adapter.out.keycloak;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/18/2026 - 12:54 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.KeycloakAdminClientTemp;
import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.KeycloakTokenResponse;
import org.springframework.stereotype.Component;

@Component
public class KeycloakPasswordTokenRetriever implements KeycloakTokenGateway<String> {

    private final KeycloakAdminClientTemp keycloakAdminClientTemp;

    public KeycloakPasswordTokenRetriever(KeycloakAdminClientTemp keycloakAdminClientTemp) {
        this.keycloakAdminClientTemp = keycloakAdminClientTemp;
    }

    @Override
    public String retrieveToken(String s) {
        KeycloakTokenResponse keycloakTokenResponse = keycloakAdminClientTemp.getChatBotAccessToken();

        if (keycloakTokenResponse != null)
            return keycloakTokenResponse.getAccessToken();
        else return null;
    }
}

