package io.projectZ.orchestrator.infrastructure.config.advice;
/*
  Project : Orchestrator
  Author  : AmirHFF
  Created : 8/15/2026 - 11:34 PM
*/

import io.github.amirHFF.errorCode.ErrorCodeBuilder;
import io.github.amirHFF.handler.GlobalExceptionsHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ChatExceptionAdvice extends GlobalExceptionsHandler {
    private final Logger logger = LogManager.getLogger(ChatExceptionAdvice.class);

    public ChatExceptionAdvice() {
        ErrorCodeBuilder.moduleName = "core";

    }
}

