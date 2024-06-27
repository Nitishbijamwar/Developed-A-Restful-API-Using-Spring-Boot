package com.training.app;

public class ApiResponseDto {

	private Object content = null;
	private Boolean error = Boolean.FALSE;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}
}
