package io.projectZ.orchestrator.ai.controller.dto.request;

import io.projectZ.orchestrator.persistence.relational.entity.AiModelType;

public record AiModelRequest(String name , String title , String apiToken , String url  , AiModelType type , Double temperature) {
}
