package io.projectZ.orchestrator.ai.service;

import io.projectZ.orchestrator.ai.DBRepository.AiModelRepository;
import io.projectZ.orchestrator.ai.model.AIModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiModelServiceImpl implements AiModelService {

	private final AiModelRepository aiModelRepository;

	public AiModelServiceImpl(AiModelRepository aiModelRepository) {
		this.aiModelRepository = aiModelRepository;
	}

	@Override
	public List<AIModel> getAllModels() {
		return aiModelRepository.findAll();
	}

	@Override
	public void save(AIModel model) {
		aiModelRepository.saveModel(model);
	}

	@Override
	public void update(AIModel model) {
		aiModelRepository.updateModel(model);
	}
}
