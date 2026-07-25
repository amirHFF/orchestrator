package io.projectZ.orchestrator.DBRepository.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 9:51 AM
*/

import io.projectZ.orchestrator.entity.PromptEntity;
import io.projectZ.orchestrator.model.PromptModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromptMapper extends BaseMapper<PromptEntity , PromptModel> {
    PromptMapper getInstance = Mappers.getMapper(PromptMapper.class);
}

