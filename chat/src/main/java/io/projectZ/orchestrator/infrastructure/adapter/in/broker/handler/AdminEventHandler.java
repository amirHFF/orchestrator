package io.projectZ.orchestrator.infrastructure.adapter.in.broker.handler;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/6/2026 - 12:30 AM
*/

import io.projectZ.orchestrator.application.service.ProfileService;
import io.projectZ.orchestrator.entity.ChatProfile;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.AdminProfileEventDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.broker.dto.EventDTO;
import org.springframework.stereotype.Component;


@Component("adminEvent")
public class AdminEventHandler implements EventHandler {
    private final ProfileService profileService;

    public AdminEventHandler(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public void handle(EventDTO eventDTO) {
        if (eventDTO instanceof AdminProfileEventDto adminEventDto) {
            ChatProfile chatProfile = mapAdminEventToChatProfile(adminEventDto);
            switch (adminEventDto.getOperationType()) {
                case "CREATE" -> profileService.saveProfile(chatProfile);
                case "UPDATE" -> profileService.updateProfile(chatProfile);
                case "DELETE" -> profileService.remove(chatProfile.getUsername());
            }
        }
    }
    private ChatProfile mapAdminEventToChatProfile(AdminProfileEventDto userEventDto){
        ChatProfile chatProfile = new ChatProfile();
        chatProfile.setUsername(userEventDto.getDetails().get("username"));
        chatProfile.setFirstName(userEventDto.getDetails().get("first_name"));
        chatProfile.setLastname(userEventDto.getDetails().get("last_name"));
        chatProfile.setKeycloakId(userEventDto.getId());

//        chatProfile.setBirthDate(userEventDto.getDetails().get("lastname"));
        return chatProfile;
    }
}

