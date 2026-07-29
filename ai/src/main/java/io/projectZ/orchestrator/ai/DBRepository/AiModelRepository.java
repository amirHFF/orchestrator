package io.projectZ.orchestrator.ai.DBRepository;

import io.projectZ.orchestrator.ai.model.AIModel;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AiModelRepository {
	AIModel getModelByName(String name);

	List<AIModel> findAll();

	void saveModel(AIModel model);

	void deleteModel(long id);

	void updateModel(AIModel model);

}
