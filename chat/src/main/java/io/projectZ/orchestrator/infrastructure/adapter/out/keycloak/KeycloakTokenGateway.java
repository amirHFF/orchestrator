package io.projectZ.orchestrator.infrastructure.adapter.out.keycloak;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/18/2026 - 12:51 PM
*/

public interface KeycloakTokenGateway<E> {
    String retrieveToken(E e);
}

