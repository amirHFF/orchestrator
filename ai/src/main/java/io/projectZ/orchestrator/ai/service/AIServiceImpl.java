package io.projectZ.orchestrator.ai.service;

import io.projectZ.orchestrator.ai.DBRepository.AiModelRepository;
import io.projectZ.orchestrator.ai.controller.dto.request.AiMessageRequestDto;
import io.projectZ.orchestrator.ai.coordinator.AiCoordinator;
import io.projectZ.orchestrator.ai.model.AIModel;
import io.projectZ.orchestrator.ai.model.AiActorModel;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {
	private final AiCoordinator<String> aiCoordinator;
	private  final AiModelRepository aiModelRepository;

	public AIServiceImpl(AiCoordinator<String> aiCoordinator, AiModelRepository aiModelRepository) {
		this.aiCoordinator = aiCoordinator;
		this.aiModelRepository = aiModelRepository;
	}

	@Override
	public String process(AiMessageRequestDto request) {
		AIModel aiModel = aiModelRepository.getModelByName(request.model());
		AiActorModel actorModel = new AiActorModel();
		actorModel.setUsername(request.username());
		actorModel.setModel(aiModel);
		actorModel.setPromptTemplateCode(request.templatePromptCode());
		return aiCoordinator.processMessage(actorModel , request.content());
	}
}
