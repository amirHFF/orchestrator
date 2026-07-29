package io.projectZ.orchestrator.ai.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AiActorModel {

	private String username;
	private String accessToken;
	private String promptTemplateCode;
	private AIModel model;

}
