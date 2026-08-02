package io.projectZ.orchestrator.ai.controller;

import io.projectZ.orchestrator.ai.controller.dto.request.AiModelRequest;
import io.projectZ.orchestrator.ai.controller.dto.response.AiModelResponse;
import io.projectZ.orchestrator.ai.model.AIModel;
import io.projectZ.orchestrator.ai.service.AiModelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(path = "/models")
public class AiModelController {

	private final AiModelService aiModelService;

	public AiModelController(AiModelService aiModelService) {
		this.aiModelService = aiModelService;
	}

	@GetMapping
	public ResponseEntity<List<AiModelResponse>> getAllModels() {
		List<AIModel> aiModelResponses = aiModelService.getAllModels();
		return ResponseEntity.ok(aiModelResponses.stream().map(x -> new AiModelResponse(x.getName() , x.getTitle(), x.getApiKey() , x.getUrl())).collect(Collectors.toList()));
	}
	@PostMapping
	public ResponseEntity<Boolean> create(@RequestBody AiModelRequest aiModelRequest) {

		AIModel aiModel = new AIModel();
		aiModel.setName(aiModelRequest.name());
		aiModel.setTitle(aiModelRequest.title());
		aiModel.setApiKey(aiModelRequest.apiToken());
		aiModel.setUrl(aiModelRequest.url());
		aiModel.setType(aiModelRequest.type());
		aiModel.setTemperature(aiModelRequest.temperature());

		aiModelService.save(aiModel);
		return ResponseEntity.ok(true);
	}
	@PatchMapping(path = "/{name}")
	public ResponseEntity<Boolean> update(@RequestBody AiModelRequest aiModelRequest) {

		AIModel aiModel = new AIModel();
		aiModel.setName(aiModelRequest.name());
		aiModel.setTitle(aiModelRequest.title());
		aiModel.setApiKey(aiModel.getApiKey());
		aiModel.setUrl(aiModel.getUrl());
		aiModel.setType(aiModelRequest.type());
		aiModel.setTemperature(aiModelRequest.temperature());

		aiModelService.update(aiModel);
		return ResponseEntity.ok(true);
	}

}
