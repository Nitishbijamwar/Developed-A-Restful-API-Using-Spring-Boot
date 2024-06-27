package com.training.app.auth;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class CustomAuthenticationException extends AuthenticationException {

	public CustomAuthenticationException(String msg) {
		super(msg);
	}

}
