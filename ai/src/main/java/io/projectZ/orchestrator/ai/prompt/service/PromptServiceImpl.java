package io.projectZ.orchestrator.ai.prompt.service;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 12:08 AM
*/

import io.projectZ.orchestrator.ai.model.PromptModel;
import io.projectZ.orchestrator.ai.model.PromptType;
import io.projectZ.orchestrator.ai.prompt.repo.PromptPersistencePort;
import io.projectZ.orchestrator.persistence.entity.PromptTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromptServiceImpl implements PromptService {

    private final Logger logger = LogManager.getLogger(PromptServiceImpl.class);
    @Autowired
    private PromptPersistencePort persistencePort;


    @Override
    public void saveOrUpdate(PromptModel promptModel) {
        PromptModel loadedPrompt = persistencePort.get(promptModel.getCode());
        if (loadedPrompt == null) {
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
    public List<PromptModel> getAllByListOfCodes(List<String> codes) {
        return persistencePort.getAllByCodeList(codes);
    }

    @Override
    public List<PromptModel> getByTypesSelectHigherPriority(List<PromptType> promptTypeList) {
        List<PromptModel> promptModelList =  persistencePort.getAllByType(promptTypeList.stream()
                .map(promptType -> PromptTypeEnum.valueOf(promptType.name())).collect(Collectors.toList()));

        return promptModelList;
    }
}

