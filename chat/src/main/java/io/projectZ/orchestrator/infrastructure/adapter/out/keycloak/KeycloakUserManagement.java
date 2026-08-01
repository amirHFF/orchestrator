package io.projectZ.orchestrator.infrastructure.adapter.out.keycloak;

import io.projectZ.orchestrator.application.port.UserManagementPort;
import io.projectZ.orchestrator.entity.BotUser;
import io.projectZ.orchestrator.entity.UserBase;
import io.projectZ.orchestrator.infrastructure.config.SecurityConfig;
import io.projectZ.orchestrator.userManagement.dto.Credential;
import io.projectZ.orchestrator.userManagement.dto.KeyClockUserDto;
import io.projectZ.orchestrator.userManagement.dto.UserRepresentation;
import io.projectZ.orchestrator.userManagement.keycloakRestClient.KeycloakAdminClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class KeycloakUserManagement implements UserManagementPort {
    private final Logger logger = LogManager.getLogger(KeycloakUserManagement.class);
    private final KeycloakAdminClient keycloakAdminClient;

    public KeycloakUserManagement(KeycloakAdminClient keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }

    @Override
    public String registerUser(UserBase userBase) {
        String keyCloakId = null;
        try {
            List<UserRepresentation> result = keycloakAdminClient.findByUsername(userBase.getUsername(), SecurityConfig.API_TOKEN);
            if (!result.isEmpty()) {
                logger.info("{} user find from keycloak", userBase.getUsername());
                keyCloakId = result.stream().findFirst().get().getId();
            } else
                keyCloakId = keycloakAdminClient.registerUser(createKeyClockUser(userBase), SecurityConfig.API_TOKEN);
        } catch (Exception e) {
            logger.error("registering user failed : ", e);
            throw new RestClientException("registering user failed : ", e);
        }
        return keyCloakId;
    }

    private KeyClockUserDto createKeyClockUser(UserBase userBaseDto) {
        KeyClockUserDto keyClockUserDto = new KeyClockUserDto();
        keyClockUserDto.setUsername(userBaseDto.getUsername());
        keyClockUserDto.setEmail(userBaseDto.getEmail());
        keyClockUserDto.setFirstName(userBaseDto.getFirstname());
        keyClockUserDto.setLastName(userBaseDto.getLastname());
        if (userBaseDto instanceof BotUser) {
            keyClockUserDto.setCredentials(List.of(new Credential("password", ((BotUser) userBaseDto).getPassword(), false)));
        }
        return keyClockUserDto;
    }
}
