package io.projectZ.orchestrator.ai.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AiProxyModel {

	private String proxyUsername;
	private String realUsername;
	private String accessToken;
	private PromptModel prompt;
	private AIModel model;

}
