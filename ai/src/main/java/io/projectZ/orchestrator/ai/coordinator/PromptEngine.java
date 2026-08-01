package io.projectZ.orchestrator.ai.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/30/2026 - 2:53 PM
*/

import io.projectZ.orchestrator.ai.model.PromptModel;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;
import java.util.Map;

public interface PromptEngine {
    Prompt render(PromptModel promptModels , Map<String , String> variableMap);
}
