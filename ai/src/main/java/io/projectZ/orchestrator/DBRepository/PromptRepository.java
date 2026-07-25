package io.projectZ.orchestrator.DBRepository;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 9:48 AM
*/

import io.projectZ.orchestrator.DBRepository.mapper.PromptMapper;
import io.projectZ.orchestrator.persistence.dao.JpaPromptRepository;
import io.projectZ.orchestrator.persistence.entity.PromptEntity;
import io.projectZ.orchestrator.model.PromptModel;
import io.projectZ.orchestrator.persistence.entity.PromptTypeEnum;
import io.projectZ.orchestrator.prompt.repo.PromptPersistencePort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class PromptRepository implements PromptPersistencePort {

    private final JpaPromptRepository jpaRepository;

	public PromptRepository(JpaPromptRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

    @Override
    public List<PromptModel> getAllRelatedPrompts(String code) {
        List<PromptEntity> promptEntities = jpaRepository.findAllByParentPromptCodeOrCode(code, code);
        return promptEntities.stream().map(PromptMapper.getInstance::entityToModel).collect(Collectors.toList());
    }

    @Override
    public PromptModel get(String code) {
        PromptEntity entity= jpaRepository.findByCode(code);
        return PromptMapper.getInstance.entityToModel(entity);
    }

    @Override
    @Transactional
    public void save(PromptModel promptModel) {
        PromptEntity entity = PromptMapper.getInstance.modelToEntity(promptModel);
        jpaRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(PromptModel promptModel) {
        PromptEntity loaded = jpaRepository.findByCode(promptModel.getCode());
        if (loaded !=null){
            loaded.setPrompt(promptModel.getPrompt());
            loaded.setTitle(promptModel.getTitle());
            loaded.setParentPromptCode(promptModel.getParent());
            loaded.setPromptType(PromptTypeEnum.valueOf(promptModel.getPromptType().name()));
        }else {
            throw new RuntimeException("prompt not found");
        }
    }

    @Override
    public void remove(String code) {
        PromptEntity loaded = jpaRepository.findByCode(code);
        if (loaded !=null){
            jpaRepository.delete(loaded);
        }else {
            throw new RuntimeException("prompt not found");
        }
    }

}

