package io.projectZ.orchestrator.controller.dto.request;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 9:03 PM
*/

import io.projectZ.orchestrator.model.PromptType;

public class PromptResponseDto {
    private String prompt ;
    private String title;
    private String code;
    private String parentPrompt;
    private PromptType promptType;

    public String getParentPrompt() {
        return parentPrompt;
    }

    public void setParentPrompt(String parentPrompt) {
        this.parentPrompt = parentPrompt;
    }

    public PromptType getPromptType() {
        return promptType;
    }

    public void setPromptType(PromptType promptType) {
        this.promptType = promptType;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

