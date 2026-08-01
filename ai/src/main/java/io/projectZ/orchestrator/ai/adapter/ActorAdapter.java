package io.projectZ.orchestrator.ai.adapter;

import io.projectZ.orchestrator.ai.model.AiProxyModel;

public interface ActorAdapter {
	AiProxyModel getActor(String id);
}
