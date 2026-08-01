package io.projectZ.orchestrator.ai.service;

import io.projectZ.orchestrator.ai.model.AIModel;

import java.util.List;

public interface AiModelService {
	List<AIModel> getAllModels();
	AIModel getModelByName(String name);

	void save(AIModel model);

	void update(AIModel model);
}
