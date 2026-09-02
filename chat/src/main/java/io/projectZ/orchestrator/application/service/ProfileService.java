package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/20/2026 - 1:19 PM
*/

import io.projectZ.orchestrator.entity.ChatProfile;

public interface ProfileService {
    void syncProfiles();

    boolean saveProfile(ChatProfile chatProfile);
    boolean updateProfile(ChatProfile chatProfile);
    boolean remove(String username);
}

