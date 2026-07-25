package io.projectZ.orchestrator.model;
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
    private String prompt;
    private String code;
    private String title;
    private String parent;
    private PromptType promptType;

}

