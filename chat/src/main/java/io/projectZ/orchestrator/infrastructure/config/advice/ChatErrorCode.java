 package io.projectZ.orchestrator.infrastructure.config.advice;

import io.github.amirHFF.errorCode.ErrorCode;
import io.github.amirHFF.errorCode.ErrorCodeBuilder;

 public enum ChatErrorCode implements ErrorCode {

	DUPLICATE_CONVERSATION("conversation is existed before" , ErrorCodeBuilder.build("4001")),
	USER_NAME_NOT_FOUND("username not found" , ErrorCodeBuilder.build("2001"));
	;

	private String message;
	private String code;

	ChatErrorCode(String message, String code) {
		this.message = message;
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
