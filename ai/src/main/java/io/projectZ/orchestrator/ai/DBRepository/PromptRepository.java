package io.projectZ.orchestrator.ai.DBRepository;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 9:48 AM
*/

import io.projectZ.orchestrator.ai.DBRepository.mapper.PromptMapper;
import io.projectZ.orchestrator.ai.model.PromptType;
import io.projectZ.orchestrator.persistence.dao.JpaPromptRepository;
import io.projectZ.orchestrator.persistence.entity.PromptEntity;
import io.projectZ.orchestrator.ai.model.PromptModel;
import io.projectZ.orchestrator.persistence.entity.PromptTypeEnum;
import io.projectZ.orchestrator.ai.prompt.repo.PromptPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PromptRepository implements PromptPersistencePort {

    private final JpaPromptRepository jpaRepository;

    public PromptRepository(JpaPromptRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PromptModel get(String code) {
        PromptEntity entity = jpaRepository.findByCode(code);
        return PromptMapper.getInstance.entityToModel(entity);
    }

    @Override
    public List<PromptModel> getAllByCodeList(List<String> codes) {
        List<PromptEntity> promptEntityList = jpaRepository.findAllByCodeIn(codes);
        return promptEntityList.stream().map(PromptMapper.getInstance::entityToModel).collect(Collectors.toList());
    }

    @Override
    public List<PromptModel> getAllByType(List<PromptTypeEnum> promptTypeList) {
        List<PromptEntity> promptEntities = jpaRepository.findAllByPromptType(promptTypeList.stream().map(PromptTypeEnum::name).collect(Collectors.toList()));

        return promptEntities.stream().map(PromptMapper.getInstance::entityToModel).collect(Collectors.toList());
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
        if (loaded != null) {
            loaded.setContent(promptModel.getContent());
            loaded.setTitle(promptModel.getTitle());
            loaded.setPromptType(PromptTypeEnum.valueOf(promptModel.getPromptType().name()));
            jpaRepository.save(loaded);
        } else {
            throw new RuntimeException("prompt not found");
        }
    }

    @Override
    public void remove(String code) {
        PromptEntity loaded = jpaRepository.findByCode(code);
        if (loaded != null) {
            jpaRepository.delete(loaded);
        } else {
            throw new RuntimeException("prompt not found");
        }
    }

    @Override
    public Page<PromptModel> search(String code, String title, Pageable pageable) {

        if (code != null && code.isEmpty())
            code = null;
        if (title != null && title.isEmpty())
            title = null;

        Page<PromptEntity> result = jpaRepository.search(code, title, pageable);


        return result.map(PromptMapper.getInstance::entityToModel);
    }

}

