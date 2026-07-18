package io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/17/2026 - 1:32 PM
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatTalkRequest {
    private String model;
    private String persona;
    private String content;
}

