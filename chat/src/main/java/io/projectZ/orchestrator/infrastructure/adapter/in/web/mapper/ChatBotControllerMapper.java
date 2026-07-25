package io.projectZ.orchestrator.infrastructure.adapter.in.web.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 6:39 PM
*/

import io.projectZ.orchestrator.entity.ChatBot;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.request.ChatBotRequestDto;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.response.ChatBotResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChatBotControllerMapper extends BaseControllerMapper<ChatBot, ChatBotResponseDto, ChatBotRequestDto> {
    ChatBotControllerMapper getInstance = Mappers.getMapper(ChatBotControllerMapper.class);
}

