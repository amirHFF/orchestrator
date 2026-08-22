package io.projectZ.orchestrator.application.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:14 PM
*/

import io.projectZ.orchestrator.application.port.ConversationPort;
import io.projectZ.orchestrator.entity.Conversation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Set;

public interface ConversationService {
    List<Conversation> getAllConversationsByUsername(String username);
    void  save(Conversation conversation);
    void  update(Conversation conversation);

}

