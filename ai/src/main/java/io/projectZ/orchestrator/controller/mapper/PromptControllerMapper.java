package io.projectZ.orchestrator.controller.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 2:36 AM
*/

import io.projectZ.orchestrator.controller.dto.request.PromptRequestDto;
import io.projectZ.orchestrator.controller.dto.request.PromptResponseDto;
import io.projectZ.orchestrator.model.PromptModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromptControllerMapper extends BaseControllerMapper<PromptModel , PromptResponseDto , PromptRequestDto> {
    static PromptControllerMapper getInstance = Mappers.getMapper(PromptControllerMapper.class);

    @Override
    PromptModel requestToModel(PromptRequestDto request);

    @Override
    PromptResponseDto ModelToResponse(PromptModel dto);
}

