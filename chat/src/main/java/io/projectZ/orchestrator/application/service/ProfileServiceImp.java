package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/20/2026 - 1:20 PM
*/

import io.projectZ.orchestrator.application.port.ChatProfilePort;
import io.projectZ.orchestrator.entity.ChatProfile;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.ChatProfileRepository;
import io.projectZ.orchestrator.infrastructure.config.SecurityConfig;
import io.projectZ.orchestrator.userManagement.dto.UserRepresentation;
import io.projectZ.orchestrator.userManagement.keycloakRestClient.KeycloakAdminClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImp implements ProfileService{
    private final ChatProfileRepository repository;
    private final KeycloakAdminClient keycloakAdminClient;
    public ProfileServiceImp(ChatProfileRepository repository, KeycloakAdminClient keycloakAdminClient) {
        this.repository = repository;
        this.keycloakAdminClient = keycloakAdminClient;
    }

    @Override
    public void syncProfiles() {
        List<UserRepresentation> Users = keycloakAdminClient.getAllUser(0 , 50 , SecurityConfig.API_TOKEN);
        for (UserRepresentation user : Users) {
            ChatProfile chatProfile =  repository.getByUsername(user.getUsername());
            if (chatProfile == null){
                repository.save(createChatProfile(user));
            }
        }
    }
    private ChatProfile createChatProfile(UserRepresentation userRepresentation) {
        if (userRepresentation !=null) {
            ChatProfile chatProfile = new ChatProfile();
            chatProfile.setUsername(userRepresentation.getUsername());
            chatProfile.setLastname(userRepresentation.getLastName());
            chatProfile.setFirstName(userRepresentation.getFirstName());
            chatProfile.setDisplayName(userRepresentation.getFirstName().concat(" ").concat(userRepresentation.getLastName()));
            return chatProfile;
        }
        else
            throw new IllegalArgumentException("user is empty");
    }
}

