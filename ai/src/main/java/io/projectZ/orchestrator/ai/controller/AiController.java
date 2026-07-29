package io.projectZ.orchestrator.ai.controller;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 1:39 AM
*/

import io.projectZ.orchestrator.ai.controller.dto.request.AiMessageRequestDto;
import io.projectZ.orchestrator.ai.controller.dto.response.ChatTalkResponse;
import io.projectZ.orchestrator.ai.coordinator.AiCoordinator;
import io.projectZ.orchestrator.ai.service.AIServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(path = "/chat/ai")
public class AiController {
    private final AIServiceImpl aiService;

	public AiController(AIServiceImpl aiService) {
		this.aiService = aiService;
	}

	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ChatTalkResponse> talkAi(@Valid @RequestBody AiMessageRequestDto aiMessageRequestDto) {
        String response = aiService.process(aiMessageRequestDto );
        return ResponseEntity.ok().body(new ChatTalkResponse(response));
    }
}

