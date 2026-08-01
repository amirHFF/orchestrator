package io.projectZ.orchestrator.ai.service;

import io.projectZ.orchestrator.ai.DBRepository.AiModelRepository;
import io.projectZ.orchestrator.ai.coordinator.ModelFactory;
import io.projectZ.orchestrator.ai.model.AIModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiModelServiceImpl implements AiModelService {

	private final AiModelRepository aiModelRepository;

	private final ModelFactory modelFactory;

	public AiModelServiceImpl(AiModelRepository aiModelRepository, @Lazy ModelFactory modelFactory) {
		this.aiModelRepository = aiModelRepository;
        this.modelFactory = modelFactory;
    }

	@Override
	public List<AIModel> getAllModels() {
		return aiModelRepository.findAll();
	}

	@Override
	public AIModel getModelByName(String name) {
		return aiModelRepository.getModelByName(name);
	}

	@Override
	public void save(AIModel model) {
		if (model.isActive()){
			modelFactory.createChatClient(model);
		}
		aiModelRepository.saveModel(model);
	}

	@Override
	public void update(AIModel model) {
		if (model.isActive()){
			modelFactory.updateModel(model.getName() , model);
		}
		aiModelRepository.updateModel(model);
	}
}
