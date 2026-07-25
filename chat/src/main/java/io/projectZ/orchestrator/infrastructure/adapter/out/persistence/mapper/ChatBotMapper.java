package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 6:07 PM
*/

import io.projectZ.orchestrator.entity.ChatBot;
import io.projectZ.orchestrator.entity.ChatBotEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatBotMapper extends BaseMapper<ChatBotEntity , ChatBot>{
    ChatBotMapper getInstance = Mappers.getMapper(ChatBotMapper.class);
}

