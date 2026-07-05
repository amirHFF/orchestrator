package io.projectZ.orchestrator.infrastructure.adapter.in.web.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 7:07 PM
*/

import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.request.ConversationRequest;
import io.projectZ.orchestrator.infrastructure.adapter.in.web.dto.response.ConversationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConversationControllerMapper extends BaseControllerMapper<Conversation , ConversationResponse,ConversationRequest >{
    ConversationControllerMapper getInstance = Mappers.getMapper(ConversationControllerMapper.class);
}
