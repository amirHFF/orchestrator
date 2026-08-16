package io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto;

import org.apache.logging.log4j.ThreadContext;

public class ResponseDto<T> {

	private final String referenceNumber = ThreadContext.get("referenceNumber");
	private boolean isSuccessful;
	private T content;
	private String message;

	public ResponseDto(){

	}
	protected ResponseDto(boolean isSuccessful, T content) {
		this.isSuccessful = isSuccessful;
		this.content = content;
	}

	protected ResponseDto(boolean isSuccessful, T content, String message) {
		this.isSuccessful = isSuccessful;
		this.content = content;
		this.message = message;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean successful) {
		isSuccessful = successful;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

}
