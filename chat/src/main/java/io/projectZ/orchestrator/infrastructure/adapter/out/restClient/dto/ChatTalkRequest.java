package io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/17/2026 - 1:32 PM
*/

public record ChatTalkRequest(String proxyUsername , String realUsername , String model , String templatePromptCode , String content ) {
}

