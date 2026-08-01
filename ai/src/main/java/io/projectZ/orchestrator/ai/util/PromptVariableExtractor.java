package io.projectZ.orchestrator.ai.util;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 7/30/2026 - 3:22 PM
*/

import io.projectZ.orchestrator.ai.model.PromptVariableType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromptVariableExtractor {

    public static Map<PromptVariableType , List<String> > extractVariables(String text) {
//        List<VariableType> variables = new ArrayList<>();
        Map<PromptVariableType , List<String> > variableTypeListMap = new HashMap<>();
        for (PromptVariableType value : PromptVariableType.values()) {
            variableTypeListMap.put(value , new ArrayList<>());
        }

        Pattern pattern = Pattern.compile("\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String variable = matcher.group(1);
            if (variable.contains(":")){
                String[] typeVar =  variable.split(":");
                variableTypeListMap.get(PromptVariableType.valueOf(typeVar[0])).add(typeVar[1]);
            }else {
                variableTypeListMap.get(PromptVariableType.PROMPT_TYPE).add(variable);
            }
        }

        return variableTypeListMap;
    }

    // نسخه بدون تکرار (حفظ ترتیب)
    public static List<String> extractUniqueVariables(String text) {
        LinkedHashSet<String> unique = new LinkedHashSet<>();
        Pattern pattern = Pattern.compile("\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            unique.add(matcher.group(1));
        }

        return new ArrayList<>(unique);
    }
}

