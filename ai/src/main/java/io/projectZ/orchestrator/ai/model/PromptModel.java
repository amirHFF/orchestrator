package io.projectZ.orchestrator.ai.model;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 10:37 PM
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromptModel {
    private String content;
    private String code;
    private String title;
    private Integer version;
    private PromptType promptType;
    private Integer priority;
}

