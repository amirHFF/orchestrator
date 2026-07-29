package io.projectZ.orchestrator.ai.model;

import io.projectZ.orchestrator.persistence.entity.AiModelType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AIModel {
	private long id;
	@NotNull(message = "name is mandatory")
	private String name;
	private String title;
	private String apiKey;
	private String url;
	private AiModelType type;
	private long usedToken;
}
