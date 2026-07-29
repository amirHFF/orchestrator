package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:41 PM
*/

import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.persistence.entity.ConversationEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConversationMapper extends BaseMapper<ConversationEntity, Conversation> {
  ConversationMapper getInstance = Mappers.getMapper(ConversationMapper.class);

  @Mapping(source = "insertTime" , target = "lastMessageTime" )
  @Override
  Conversation entityToModel(ConversationEntity entity);

  @BeforeMapping
  public default void beforeMap(ConversationEntity conversationEntity){
    if (conversationEntity.getUpdateTime() !=null){
      conversationEntity.setInsertTime(conversationEntity.getUpdateTime());
    }
  }
}

