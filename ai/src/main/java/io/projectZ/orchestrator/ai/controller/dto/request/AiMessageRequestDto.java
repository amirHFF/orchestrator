package io.projectZ.orchestrator.ai.controller.dto.request;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/17/2026 - 1:32 PM
*/

import javax.validation.constraints.NotNull;

public record AiMessageRequestDto(@NotNull String username , @NotNull String model  , @NotNull String templatePromptCode , @NotNull String content){

}

