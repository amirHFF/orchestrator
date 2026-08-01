package io.projectZ.orchestrator.ai.prompt.repo;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 10:20 PM
*/

import io.projectZ.orchestrator.ai.model.PromptModel;
import io.projectZ.orchestrator.persistence.entity.PromptTypeEnum;

import java.util.List;

public interface PromptPersistencePort {
    PromptModel get(String code);
    List<PromptModel> getAllByCodeList(List<String> codes);
    List<PromptModel> getAllByType(List<PromptTypeEnum> promptTypeList);
    void save(PromptModel promptModel);
    void update(PromptModel promptModel);
    void remove(String code);
}

