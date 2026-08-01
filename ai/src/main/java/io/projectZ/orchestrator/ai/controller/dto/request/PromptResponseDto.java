package io.projectZ.orchestrator.ai.controller.dto.request;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 9:03 PM
*/

import io.projectZ.orchestrator.ai.model.PromptType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromptResponseDto {
    private String content ;
    private String title;
    private String code;
    private String version;
    private PromptType promptType;
    private Integer priority;

}

