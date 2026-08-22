package io.projectZ.orchestrator.infrastructure.adapter.out.persistence.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/5/2026 - 6:41 PM
*/

import io.projectZ.orchestrator.entity.Conversation;
import io.projectZ.orchestrator.persistence.entity.ChatProfileEntity;
import io.projectZ.orchestrator.persistence.entity.ConversationEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConversationMapper extends BaseMapper<ConversationEntity, Conversation> {
  ConversationMapper getInstance = Mappers.getMapper(ConversationMapper.class);

  @Mapping(source = "insertTime" , target = "lastMessageTime" )
  @Mapping(source = "participants" , target = "participants" ,qualifiedByName = "mapParticipants")
  @Override
  Conversation entityToModel(ConversationEntity entity);

  @Mapping( target = "participants" , ignore = true)
  @Override
  ConversationEntity modelToEntity(Conversation model);

  @BeforeMapping
  public default void beforeMap(ConversationEntity conversationEntity){
    if (conversationEntity.getUpdateTime() !=null){
      conversationEntity.setInsertTime(conversationEntity.getUpdateTime());
    }
  }
  @Named("mapParticipants")
  public default List<String> mapParticipants(Set<ChatProfileEntity> chatProfileEntitySet){
    List<String> participants = chatProfileEntitySet.stream().map(ChatProfileEntity::getUsername).collect(Collectors.toList());
    return participants;
  }
}

