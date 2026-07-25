package io.projectZ.orchestrator.keycloakRestClient;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/10/2026 - 11:20 PM
*/

import io.projectZ.orchestrator.dto.KeyClockUserDto;
import io.projectZ.orchestrator.dto.KeycloakTokenResponse;
import io.projectZ.orchestrator.dto.UserRepresentation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Component
public class KeycloakAdminClient {
    private final Logger logger = LogManager.getLogger(KeycloakAdminClient.class);
    private RestClient restClient = RestClient.builder().baseUrl("http://130.185.121.173:8081").build();

    public List<UserRepresentation> findByUsername(
            String username,
            String adminAccessToken
    ) {

        List<UserRepresentation> userRepresentations = restClient.get()
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

        KeycloakTokenResponse keycloakTokenResponse = restClient.post()
                .uri("/realms/project-z/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(KeycloakTokenResponse.class);

        return keycloakTokenResponse;
    }

    public KeycloakTokenResponse getChatBotAccessToken() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        form.add("grant_type", "password");
        form.add("client_id", "z-chat");
        form.add("username", "chat-bot");
        form.add("password", "123");
        form.add("scope", "openid");

        KeycloakTokenResponse tokenResponse = null;
        try {
            tokenResponse = restClient.post()
                    .uri("/realms/{realm}/protocol/openid-connect/token", "project-z")
                    .body(form)
                    .retrieve()
                    .body(KeycloakTokenResponse.class);
        } catch (Exception e) {
            logger.error("request to keycloak for getting access token failed : ", e);
        }

        return tokenResponse;
    }

    public String registerUser(KeyClockUserDto userDto, String token) {
        RestClient.ResponseSpec responseSpec = restClient.post().uri("/admin/realms/amir-api/users")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(userDto)
                .retrieve();
        if (responseSpec != null && responseSpec.toBodilessEntity().getHeaders() != null) {
            String location = responseSpec.toBodilessEntity().getHeaders().getFirst("Location");
            String keycloakId = location.substring(location.lastIndexOf("/") + 1);
            logger.info("registered userId : {}", keycloakId);
            return keycloakId;
        } else throw new RestClientException("registering user to keycloak return nothing");
    }
}

