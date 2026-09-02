package io.projectZ.orchestrator.application.port;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/20/2026 - 1:01 AM
*/

import io.projectZ.orchestrator.entity.ChatProfile;
import io.projectZ.orchestrator.persistence.entity.ChatProfileEntity;

import java.util.List;

public interface ChatProfilePort {
    ChatProfile getByUsername(String username);
    void save(ChatProfile chatProfile);
    void update(ChatProfile chatProfile);
    void remove(String username);
    void setDisplayName(String displayName , String username);
    List<ChatProfile> fetchAllUser();
}

