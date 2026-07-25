package io.projectZ.orchestrator.controller;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:39 AM
*/

import io.projectZ.orchestrator.controller.dto.request.ChatTalkRequest;
import io.projectZ.orchestrator.controller.dto.response.ChatTalkResponse;
import io.projectZ.orchestrator.coordinator.AiCoordinator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@SecurityRequirement(name = "Bearer Authentication")
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

