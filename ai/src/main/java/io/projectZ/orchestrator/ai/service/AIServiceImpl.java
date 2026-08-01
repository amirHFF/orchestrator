package io.projectZ.orchestrator.ai.service;

import io.projectZ.orchestrator.ai.DBRepository.AiModelRepository;
import io.projectZ.orchestrator.ai.controller.dto.request.AiMessageRequestDto;
import io.projectZ.orchestrator.ai.coordinator.AiCoordinator;
import io.projectZ.orchestrator.ai.model.AIModel;
import io.projectZ.orchestrator.ai.model.AiProxyModel;
import io.projectZ.orchestrator.ai.model.PromptModel;
import io.projectZ.orchestrator.ai.prompt.service.PromptService;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {
	private final AiCoordinator<String> aiCoordinator;
	private  final AiModelRepository aiModelRepository;
	private  final PromptService promptService;

	public AIServiceImpl(AiCoordinator<String> aiCoordinator, AiModelRepository aiModelRepository, PromptService promptService) {
		this.aiCoordinator = aiCoordinator;
		this.aiModelRepository = aiModelRepository;
        this.promptService = promptService;
    }

	@Override
	public String process(AiMessageRequestDto request) {
		AIModel aiModel = aiModelRepository.getModelByName(request.model());

		AiProxyModel proxy = createProxy(request , aiModel);
		return aiCoordinator.processMessage(proxy, request.content());
	}

	private AiProxyModel createProxy(AiMessageRequestDto request , AIModel aiModel){
		AiProxyModel proxy = new AiProxyModel();
		proxy.setProxyUsername(request.proxyUsername());
		proxy.setRealUsername(request.realUsername());
		proxy.setModel(aiModel);

		PromptModel promptModel = promptService.get(request.templatePromptCode());
		proxy.setPrompt(promptModel);

		return proxy;
	}
}
