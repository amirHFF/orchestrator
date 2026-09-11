package io.projectZ.orchestrator.ai.prompt.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/22/2026 - 9:07 PM
*/

import io.projectZ.orchestrator.ai.model.PromptModel;
import io.projectZ.orchestrator.ai.model.PromptType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PromptService {

    void saveOrUpdate(PromptModel promptModel);
    PromptModel get(String code);
    List<PromptModel> getAllByListOfCodes(List<String> codes);
    List<PromptModel> getByTypesSelectHigherPriority(List<PromptType> promptType);

    Page<PromptModel> search(String title , String code , Pageable pageable);

}

