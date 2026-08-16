 package io.projectZ.orchestrator.infrastructure.config.advice;

import io.github.amirHFF.errorCode.ErrorCode;

public enum ChatErrorCode implements ErrorCode {

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
