package io.projectZ.orchestrator.prompt.repo;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 10:20 PM
*/

import io.projectZ.orchestrator.model.PromptModel;

public interface PromptPersistencePort {
    PromptModel get(String code);
    void save(PromptModel promptModel);
    void update(PromptModel promptModel);
    void remove(String code);
}

