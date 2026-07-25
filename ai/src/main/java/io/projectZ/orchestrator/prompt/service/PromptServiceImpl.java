package io.projectZ.orchestrator.prompt.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 12:08 AM
*/

import io.projectZ.orchestrator.model.PromptModel;
import io.projectZ.orchestrator.prompt.repo.PromptPersistencePort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class PromptServiceImpl implements PromptService {

    private final Logger logger = LogManager.getLogger(PromptServiceImpl.class);
    @Autowired
    private PromptPersistencePort persistencePort;


    @Override
    public void saveOrUpdate(PromptModel promptModel) {
        PromptModel loadedPrompt = persistencePort.get(promptModel.getCode());
        if (loadedPrompt == null) {
            if (promptModel.getParent() != null) {
                PromptModel loadedParentPrompt = persistencePort.get(promptModel.getParent());
                if (loadedParentPrompt == null) {
                    throw new RuntimeException("parent not found");
                }
            }
            persistencePort.save(promptModel);
        } else {
            persistencePort.update(promptModel);
        }
    }

    @Override
    public PromptModel get(String code) {
        logger.info("get prompt {} ", code);
        return persistencePort.get(code);
    }

    @Override
    public List<PromptModel> getAllRelatedPrompt(String code) {
        return persistencePort.getAllRelatedPrompts(code);
    }
}

