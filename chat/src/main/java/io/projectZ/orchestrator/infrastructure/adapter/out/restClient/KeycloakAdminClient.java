package io.projectZ.orchestrator.infrastructure.adapter.out.restClient;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/10/2026 - 11:20 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto.KeycloakTokenResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class KeycloakAdminClient {
    private RestClient restClient = RestClient.builder().baseUrl("http://130.185.121.173:8081").build();

    public List<UserRepresentation> findByUsername(
            String username,
            String adminAccessToken
    ) {

        List<UserRepresentation> userRepresentations =  restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users")
                        .queryParam("username", username)
                        .queryParam("exact", true)
                        .build("project-z"))
                .header(HttpHeaders.AUTHORIZATION,
                        "Bearer " + adminAccessToken)
                .retrieve()
                .body(new ParameterizedTypeReference<List<UserRepresentation>>() {
                });

        return userRepresentations;
    }
    public KeycloakTokenResponse generateApiToken() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("grant_type", "client_credentials");
        form.add("client_id", "orchestrator-resource");
        form.add("client_secret", "RQee8cQW6oQE1E1wsn0u1s1iF2vkOL9L");

        KeycloakTokenResponse keycloakTokenResponse =  restClient.post()
                .uri("/realms/project-z/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(KeycloakTokenResponse.class);

        return keycloakTokenResponse;
    }
}

