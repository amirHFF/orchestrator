//package io.projectZ.orchestrator.ai.restClient;
//
//import io.github.AmirHFF.restCall.*;
//import io.projectZ.orchestrator.ai.restClient.dto.ChatBotResponseDto;
//import org.apache.hc.core5.http.HttpHost;
//import org.springframework.stereotype.Component;
//
//@Component
//public class `ChatRestClient` {
//	private RestApiBuilder restApi = new lowThroughputRestApi();
//
//	private HttpHost host = new HttpHost("http://localhost:8090");
//	private RestClientSeed restTemplate = restApi.build(host);
//
//	public ChatBotResponseDto fetchChatBotByID(String botID) {
//
//		ChatBotResponseDto chatBotResponseDto = null;
//		return new RestClient.Builder().setEndpoint("bot/{botID}")
//				.setPathVariable("botID", botID)
//				.build(ChatBotResponseDto.class);
//	}
//
//}
