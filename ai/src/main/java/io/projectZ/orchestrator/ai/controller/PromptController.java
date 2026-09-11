package io.projectZ.orchestrator.ai.controller;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 11:55 AM
*/

import io.projectZ.orchestrator.ai.controller.dto.request.PromptRequestDto;
import io.projectZ.orchestrator.ai.controller.dto.request.PromptResponseDto;
import io.projectZ.orchestrator.ai.controller.dto.response.PageResponse;
import io.projectZ.orchestrator.ai.controller.mapper.PromptControllerMapper;
import io.projectZ.orchestrator.ai.model.PromptModel;
import io.projectZ.orchestrator.ai.prompt.service.PromptService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/prompts")
public class PromptController {
    private final PromptService promptService;

    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Boolean> addOrUpdatePrompt(@RequestBody PromptRequestDto prompt) {
        if (prompt.getIsBase64() != null && prompt.getIsBase64()) {
            prompt.setContent(new String(Base64.getDecoder().decode(prompt.getContent())));
        }

        promptService.saveOrUpdate(PromptControllerMapper.getInstance.requestToModel(prompt));
        return ResponseEntity.ok().body(true);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{code}")
    public ResponseEntity<PromptResponseDto> getPrompt(@PathVariable String code) {

        PromptModel promptModel = promptService.get(code);
        return ResponseEntity.ok().body(PromptControllerMapper.getInstance.ModelToResponse(promptModel));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public ResponseEntity<PageResponse<List<PromptResponseDto>>> getPromptList(@RequestParam(required = false) String code,
                                                                               @RequestParam(required = false) String title,
                                                                               @RequestParam(required = false , defaultValue = "20") int size ,
                                                                               @RequestParam(required = false , defaultValue = "0") int page) {


        Page<PromptModel> promptModelResult = promptService.search(title, code, PageRequest.of(page, size));
        PageResponse<List<PromptResponseDto>> response = new PageResponse<>(promptModelResult.get().map(PromptControllerMapper.getInstance::ModelToResponse).collect(Collectors.toList()),
                promptModelResult.getTotalElements(),
                promptModelResult.getTotalPages(),
                promptModelResult.getSize());

        return ResponseEntity.ok().body(response);
    }

}

