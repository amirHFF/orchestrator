package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/20/2026 - 1:20 PM
*/

import io.github.amirHFF.exceptions.BadInputException;
import io.projectZ.orchestrator.application.port.ChatProfilePort;
import io.projectZ.orchestrator.entity.ChatProfile;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.relational.ChatProfileRepository;
import io.projectZ.orchestrator.infrastructure.config.SecurityConfig;
import io.projectZ.orchestrator.infrastructure.config.advice.ChatErrorCode;
import io.projectZ.orchestrator.userManagement.dto.UserRepresentation;
import io.projectZ.orchestrator.userManagement.keycloakRestClient.KeycloakAdminClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImp implements ProfileService{
    private final ChatProfilePort chatProfilePort;
    private final KeycloakAdminClient keycloakAdminClient;
    public ProfileServiceImp(ChatProfilePort chatProfilePort, KeycloakAdminClient keycloakAdminClient) {
        this.chatProfilePort = chatProfilePort;
        this.keycloakAdminClient = keycloakAdminClient;
    }

    @Override
    public void syncProfiles() {
        List<UserRepresentation> Users = keycloakAdminClient.getAllUser(0 , 50 , SecurityConfig.API_TOKEN);
        for (UserRepresentation user : Users) {
            ChatProfile chatProfile =  chatProfilePort.getByUsername(user.getUsername());
            if (chatProfile == null){
                chatProfilePort.save(createChatProfile(user));
            }
        }
    }

    @Override
    public boolean saveProfile(ChatProfile chatProfile) {
        if (chatProfile.getUsername() == null){
            throw new BadInputException(ChatErrorCode.USER_NAME_IS_MANDATORY);
        }
        chatProfilePort.save(chatProfile);
        return true;
    }

    @Override
    public boolean updateProfile(ChatProfile chatProfile) {
        if (chatProfile.getUsername() == null){
            throw new BadInputException(ChatErrorCode.USER_NAME_IS_MANDATORY);
        }
        chatProfilePort.update(chatProfile);
        return true;
    }

    @Override
    public boolean remove(String username) {
        if (username == null){
            throw new BadInputException(ChatErrorCode.USER_NAME_IS_MANDATORY);
        }
        chatProfilePort.remove(username);
        return true;
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

