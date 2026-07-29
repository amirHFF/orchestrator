package io.projectZ.orchestrator.ai.service;

import io.projectZ.orchestrator.ai.controller.dto.request.AiMessageRequestDto;

public interface AIService {
	String process(AiMessageRequestDto request);
}
