package io.projectZ.orchestrator.controller.dto.request;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 9:03 PM
*/

import io.projectZ.orchestrator.model.PromptType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromptRequestDto {
    private String content ;
    private String title;
    private String code;
    private PromptType promptType;
    private Boolean isBase64;

}

