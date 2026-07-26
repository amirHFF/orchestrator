package io.projectZ.orchestrator.prompt.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 9:07 PM
*/

import io.projectZ.orchestrator.model.PromptModel;

import java.util.List;

public interface PromptService {

    void saveOrUpdate(PromptModel promptModel);
    PromptModel get(String code);

}

