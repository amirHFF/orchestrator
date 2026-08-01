package io.projectZ.orchestrator.ai.adapter;

import io.projectZ.orchestrator.ai.DBRepository.AiModelRepository;
import io.projectZ.orchestrator.ai.model.AiProxyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ChatBotActorAdapter implements ActorAdapter {
	private Logger logger = LogManager.getLogger(ChatBotActorAdapter.class);

	private final AiModelRepository aiModelRepository;

	public ChatBotActorAdapter(AiModelRepository aiModelRepository) {
		this.aiModelRepository = aiModelRepository;
	}

	@Override
	public AiProxyModel getActor(String id) {
		AiProxyModel aiProxyModel = null;
		try {
//			ChatBotResponseDto chatBotResponseDto = chatRestClient.fetchChatBotByID(id);

		}catch (Exception e){
			logger.error("fetch chat bot failed :",e);
		}

		return null;
	}

//	private AiActorModel createActorModel (ChatBotResponseDto chatBotResponseDto) {
//		aiModelRepository.getModelByName(chatBotResponseDto.)
//		AiActorModel aiActorModel = new AiActorModel();
////		aiActorModel.setName(chatBotResponseDto.name());
//		aiActorModel.setUsername(chatBotResponseDto.botID());
//		aiActorModel.setPromptTemplateCode(chatBotResponseDto.promptTemplateCode());
//		aiActorModel.setModel();
//	}
}
