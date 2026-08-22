package io.projectZ.orchestrator.userManagement.keycloakRestClient;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/10/2026 - 11:20 PM
*/

import io.projectZ.orchestrator.userManagement.dto.KeyClockUserDto;
import io.projectZ.orchestrator.userManagement.dto.KeycloakTokenResponse;
import io.projectZ.orchestrator.userManagement.dto.UserRepresentation;
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
    private RestClient restClient = RestClient.builder().baseUrl("https://auth.simorq.top").build();


    public List<UserRepresentation> findByUsername(
            String username,
            String adminAccessToken
    ) {
        logger.info("finding user from keycloak ...");
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


    public List<UserRepresentation> getAllUser(Integer first , Integer max , String adminAccessToken){
        List<UserRepresentation> userRepresentations = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/admin/realms/{realm}/users")
                        .queryParam("first", first)
                        .queryParam("max", max)
                        .build("project-z"))
                .header(HttpHeaders.AUTHORIZATION,
                        "Bearer " + adminAccessToken)
                .retrieve()
                .body(new ParameterizedTypeReference<List<UserRepresentation>>() {
                });
        return userRepresentations;
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
        try {
            ResponseEntity<Void> response = restClient.post()
                    .uri("/admin/realms/project-z/users")
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userDto)
                    .retrieve()
                    .toBodilessEntity();

            if (response.getStatusCode().is2xxSuccessful() && response.getHeaders().getLocation() != null) {
                String location = response.getHeaders().getLocation().toString();
                String keycloakId = location.substring(location.lastIndexOf("/") + 1);
                logger.info("Registered userId: {}", keycloakId);
                return keycloakId;
            } else {
                throw new RestClientException("Registration failed with status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("Error registering user in Keycloak", e);
            throw new RestClientException("Failed to register user", e);
        }
    }
}

