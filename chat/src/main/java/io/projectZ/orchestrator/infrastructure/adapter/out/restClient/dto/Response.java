package io.projectZ.orchestrator.infrastructure.adapter.out.restClient.dto;

public abstract class Response {

	public static <T> ResponseDto<T> success(T content) {
		return new ResponseDto<T>(true, content);
	}

	public static <T> ResponseDto<T> success(T content, String message) {
		return new ResponseDto<>(true, content, message);
	}

	public static <T> ResponseDto<T> failure(T content, String message) {
		return new ResponseDto<>(false, content, message);
	}
}
