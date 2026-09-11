package io.projectZ.orchestrator.ai.controller.dto.response;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/9/2026 - 1:19 AM
*/

public record PageResponse<D>(D result ,long total , int page , int size ) {
}

