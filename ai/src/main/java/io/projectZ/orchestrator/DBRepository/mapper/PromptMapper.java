package io.projectZ.orchestrator.DBRepository.mapper;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/23/2026 - 9:51 AM
*/

import io.projectZ.orchestrator.ai.DBRepository.mapper.BaseMapper;
import io.projectZ.orchestrator.model.PromptType;
import io.projectZ.orchestrator.persistence.entity.PromptEntity;
import io.projectZ.orchestrator.model.PromptModel;
import io.projectZ.orchestrator.persistence.entity.PromptTypeEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromptMapper extends BaseMapper<PromptEntity, PromptModel> {
	static PromptMapper getInstance = Mappers.getMapper(PromptMapper.class);

	@Mapping(source = "promptType", target = "promptType", qualifiedByName = "getPromptTypeEnum")
	@Override
	PromptEntity modelToEntity(PromptModel model);

	@Mapping(source = "promptType", target = "promptType", qualifiedByName = "getPromptType")
	@Override
	PromptModel entityToModel(PromptEntity entity);

	@Named(value = "getPromptType")
	public default PromptType getPromptType(PromptTypeEnum promptTypeEnum) {
		if (promptTypeEnum != null)
			return PromptType.valueOf(promptTypeEnum.name());
		else return null;
	}

	@Named(value = "getPromptTypeEnum")
	public default PromptTypeEnum getPromptTypeEnum(PromptType promptType) {
		if (promptType != null)
		return PromptTypeEnum.valueOf(promptType.name());
		else return null;
	}
}

