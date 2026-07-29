package io.projectZ.orchestrator.ai.controller;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 11:55 AM
*/

import io.projectZ.orchestrator.controller.dto.request.PromptRequestDto;
import io.projectZ.orchestrator.controller.dto.request.PromptResponseDto;
import io.projectZ.orchestrator.controller.mapper.PromptControllerMapper;
import io.projectZ.orchestrator.model.PromptModel;
import io.projectZ.orchestrator.prompt.service.PromptService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/prompts")
public class PromptController {
    private final PromptService promptService;

    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Boolean> addOrUpdatePrompt(@RequestBody PromptRequestDto prompt ) {
        if (prompt.getIsBase64() !=null && prompt.getIsBase64()) {
            prompt.setContent(new String(Base64.getDecoder().decode(prompt.getContent())));
        }

        promptService.saveOrUpdate(PromptControllerMapper.getInstance.requestToModel(prompt));
        return ResponseEntity.ok().body(true);
    }
    @RequestMapping(method = RequestMethod.GET , path = "/{code}")
    public ResponseEntity<PromptResponseDto> getPrompt(@PathVariable String code) {

        PromptModel promptModel = promptService.get(code);
        return ResponseEntity.ok().body(PromptControllerMapper.getInstance.ModelToResponse(promptModel));
    }
}

