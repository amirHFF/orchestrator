package io.projectZ.orchestrator.controller.dto;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/16/2026 - 11:54 PM
*/


public class ChatTalkResponse {
    public ChatTalkResponse(String content) {
        this.content = content;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

