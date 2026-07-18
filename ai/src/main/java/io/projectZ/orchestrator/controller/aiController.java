package io.projectZ.orchestrator.controller;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:39 AM
*/

import io.projectZ.orchestrator.controller.dto.ChatTalkRequest;
import io.projectZ.orchestrator.controller.dto.ChatTalkResponse;
import io.projectZ.orchestrator.coordinator.AiCoordinator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/chat/ai")
public class aiController {
    AiCoordinator<String> aiCoordinator;

    public aiController(AiCoordinator<String> aiCoordinator) {
        this.aiCoordinator = aiCoordinator;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ChatTalkResponse> talkAi(@RequestBody ChatTalkRequest message , @RequestHeader("sessionId")String sessionId) {
        String response = aiCoordinator.processMessage(message , sessionId);
        return ResponseEntity.ok().body(new ChatTalkResponse(response));
    }

}

