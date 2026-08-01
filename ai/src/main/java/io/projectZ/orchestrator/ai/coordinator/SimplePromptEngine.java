package io.projectZ.orchestrator.ai.coordinator;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/30/2026 - 2:55 PM
*/

import io.projectZ.orchestrator.ai.model.PromptModel;
import io.projectZ.orchestrator.ai.model.PromptType;
import io.projectZ.orchestrator.ai.model.PromptVariableType;
import io.projectZ.orchestrator.ai.prompt.service.PromptService;
import io.projectZ.orchestrator.ai.util.PromptVariableExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SimplePromptEngine implements PromptEngine {
    private Logger logger = LogManager.getLogger(SimplePromptEngine.class);

    private final PromptService promptService;

    public SimplePromptEngine(PromptService promptService) {
        this.promptService = promptService;
    }

    @Override
    public Prompt render(PromptModel promptModel, Map<String, String> variableMap) {
        Map<String, Object> resultMap = new HashMap<>();
        PromptTemplate promptTemplate = new PromptTemplate(promptModel.getContent());
        Map<PromptVariableType, List<String>> variableTypeListMap = PromptVariableExtractor.extractVariables(promptModel.getContent());

        variableTypeListMap.forEach((type, variableList) -> {
            switch (type) {
                case ARGUMENT -> {
                    if (variableMap != null) {
                        for (String var : variableList) {
                            resultMap.put(getResultMapCorrectKey(type.name(), var), variableMap.get(var));
                        }
                    }
                }
                case PROMPT_TYPE -> {
                    if (variableList.size() > 0) {
                        List<PromptModel> promptModelList = promptService.getByTypesSelectHigherPriority(variableList.stream().map(PromptType::valueOf).collect(Collectors.toList()));
                        for (String var : variableList) {
                            resultMap.put(getResultMapCorrectKey(type.name(), var), promptModelList.stream().filter(x -> x.getPromptType().name().equals(var)).findFirst().get().getContent());
                        }
                    }
                }
                case PROMPT_CODE -> {
                    List<PromptModel> promptModelList = promptService.getAllByListOfCodes(variableList);
                    for (String var : variableList) {
                        resultMap.put(getResultMapCorrectKey(type.name(), var), promptModelList.stream().filter(x -> x.getCode().equals(var)).findFirst().get().getContent());
                    }
                }
            }
        });

        Prompt prompt = create(promptModel.getContent() , resultMap);
        logger.info("prompt : {} " , prompt.toString());
        return prompt;
    }

    private static String getResultMapCorrectKey(String type, String variable) {
        return type.concat(":").concat(variable);
    }
    private Prompt create(String template , Map<String , Object> variableMap){

        for (Map.Entry<String, Object> entry : variableMap.entrySet()) {
            template = template.replace(
                    "{" + entry.getKey() + "}",
                    Objects.toString(entry.getValue(), "")
            );
        }
        return new Prompt(List.of(new SystemMessage(template)));
    }
}

