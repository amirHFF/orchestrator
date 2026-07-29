package io.projectZ.orchestrator.ai.DBRepository;

import io.projectZ.orchestrator.ai.DBRepository.mapper.AiModelMapper;
import io.projectZ.orchestrator.ai.model.AIModel;
import io.projectZ.orchestrator.ai.service.AiModelService;
import io.projectZ.orchestrator.persistence.dao.JpaAiModelRepository;
import io.projectZ.orchestrator.persistence.entity.AIModelEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AiModelRepositoryImpl implements AiModelRepository {
	private final Logger logger = LogManager.getLogger(AiModelService.class);
	private final JpaAiModelRepository jpaAiModelRepository;

	public AiModelRepositoryImpl(JpaAiModelRepository jpaAiModelRepository) {
		this.jpaAiModelRepository = jpaAiModelRepository;
	}

	@Override
	public AIModel getModelByName(String name) {
		logger.info("Getting model by name: {}", name);
		AIModelEntity aiModelEntity = jpaAiModelRepository.findByName(name);
		return AiModelMapper.getInstance.entityToModel(aiModelEntity);
	}


	@Override
	public List<AIModel> findAll() {
		logger.info("Getting all models");
		List<AIModelEntity> aiModelEntityList = jpaAiModelRepository.findAll();
		return aiModelEntityList.stream().map(AiModelMapper.getInstance::entityToModel).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void saveModel(AIModel model) {
		logger.info("Saving model: {}", model.getName());
		jpaAiModelRepository.save(AiModelMapper.getInstance.modelToEntity(model));
	}

	@Override
	public void deleteModel(long id) {

	}

	@Transactional
	@Override
	public void updateModel(@Valid AIModel model) {
		logger.info("Updating model: {}", model.getName());

		AIModelEntity loadedModel = jpaAiModelRepository.findByName(model.getName());

		jpaAiModelRepository.save(updateModel(loadedModel, model));
	}

	private AIModelEntity updateModel(AIModelEntity loadedModel , AIModel aiModel) {
		loadedModel.setTitle(aiModel.getTitle());
		loadedModel.setType(aiModel.getType());
		loadedModel.setApiKey(aiModel.getApiKey());
		loadedModel.setUrl(aiModel.getUrl());
		return loadedModel;
	}
}
