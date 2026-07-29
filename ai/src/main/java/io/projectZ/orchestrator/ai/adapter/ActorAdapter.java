package io.projectZ.orchestrator.ai.adapter;

import io.projectZ.orchestrator.ai.model.AiActorModel;

public interface ActorAdapter {
	AiActorModel getActor(String id);
}
