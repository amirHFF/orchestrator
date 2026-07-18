package io.projectZ.orchestrator.controller.dto;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/17/2026 - 1:32 PM
*/

public class ChatTalkRequest {
    private String model;
    private String persona;
    private String content;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

