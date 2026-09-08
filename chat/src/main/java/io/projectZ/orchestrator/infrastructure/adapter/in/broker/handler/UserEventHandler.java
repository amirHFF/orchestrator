package io.projectZ.orchestrator.infrastructure.adapter.in.broker.handler;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/1/2026 - 6:38 PM
*/

import io.projectZ.orchestrator.application.service.ProfileService;
import io.projectZ.orchestrator.entity.ChatProfile;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.AdminProfileEventDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.EventDTO;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.UserProfileEventDto;
import org.springframework.stereotype.Component;

@Component("userEvent")
public class UserEventHandler implements EventHandler {

    private ProfileService profileService;

    public UserEventHandler(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public void handle(EventDTO eventDTO) {
        if (eventDTO instanceof UserProfileEventDto userEventDto) {
            ChatProfile chatProfile = mapUserEventToChatProfile(userEventDto);
            switch (userEventDto.getEventType()) {
                case REGISTER -> profileService.saveProfile(chatProfile);
                case UPDATE_PROFILE -> profileService.updateProfile(chatProfile);
                case DELETE_ACCOUNT -> profileService.remove(chatProfile.getUsername());
            }
        }
    }

    private ChatProfile mapUserEventToChatProfile(UserProfileEventDto userEventDto) {
        ChatProfile chatProfile = new ChatProfile();
        chatProfile.setUsername(userEventDto.getDetails().get("username"));
        chatProfile.setFirstName(userEventDto.getDetails().get("first_name"));
        chatProfile.setLastname(userEventDto.getDetails().get("last_name"));
        chatProfile.setKeycloakId(userEventDto.getId());

//        chatProfile.setBirthDate(userEventDto.getDetails().get("lastname"));
        return chatProfile;
    }
}

