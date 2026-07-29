package io.projectZ.orchestrator.ai.DBRepository.mapper;

import io.projectZ.orchestrator.ai.model.AIModel;
import io.projectZ.orchestrator.persistence.entity.AIModelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AiModelMapper extends BaseMapper<AIModelEntity , AIModel>  {
	AiModelMapper getInstance = Mappers.getMapper(AiModelMapper.class);
}
