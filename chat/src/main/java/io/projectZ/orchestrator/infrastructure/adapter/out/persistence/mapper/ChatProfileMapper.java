package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/24/2026 - 6:07 PM
*/

import io.projectZ.orchestrator.application.port.ChatProfilePort;
import io.projectZ.orchestrator.entity.ChatBot;
import io.projectZ.orchestrator.entity.ChatProfile;
import io.projectZ.orchestrator.persistence.entity.ChatBotEntity;
import io.projectZ.orchestrator.persistence.entity.ChatProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChatProfileMapper extends BaseMapper<ChatProfileEntity, ChatProfile>{
    ChatProfileMapper getInstance = Mappers.getMapper(ChatProfileMapper.class);

}

