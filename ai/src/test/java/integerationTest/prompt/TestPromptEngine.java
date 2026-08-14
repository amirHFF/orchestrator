//package integerationTest.prompt;
//
//import io.projectZ.orchestrator.AiApplicationRunner;
//import io.projectZ.orchestrator.ai.coordinator.PromptEngine;
//import io.projectZ.orchestrator.ai.model.PromptModel;
//import io.projectZ.orchestrator.ai.prompt.service.PromptService;
//import org.junit.jupiter.api.Test;
//import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///*
//  Project : Orchestrator
//  Author  : AmirHFF
//  Created : 7/31/2026 - 12:59 PM
//*/
//@SpringBootTest(classes = AiApplicationRunner.class)
//public class TestPromptEngine {
//
//    @Autowired
//    private PromptService promptService;
//    @Autowired
//    private PromptEngine promptEngine;
//
//    @Test
//    public void testRenderingPrompt(){
//        PromptModel promptModel = promptService.get("template-test-1");
//        Prompt  prompt = promptEngine.render(promptModel , null );
//        System.out.println(prompt.toString());
//    }
//}
//
