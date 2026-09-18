package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.noRel;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 9/15/2026 - 7:44 PM
*/

import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.BaseMapper;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper.ChatBotMapper;
import io.projectZ.orchestrator.infrastructure.adapter.out.persistence.noRelational.ChatBotCacheDTO;
import io.projectZ.orchestrator.persistence.unrelational.redis.entity.ChatBotStatus;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChatBotCacheMapper extends BaseMapper<ChatBotStatus , ChatBotCacheDTO> {
    ChatBotCacheMapper getInstance = Mappers.getMapper(ChatBotCacheMapper.class);

}

