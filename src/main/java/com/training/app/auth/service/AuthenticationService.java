package com.training.app.auth.service;

import com.training.app.User;
import com.training.app.auth.CustomAuthenticationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("AuthenticationService")
public class AuthenticationService implements IAuthenticationService {

	@Override
	public User getSession() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			Object u = auth.getPrincipal();
			if (u instanceof User) {
				return (User) auth.getPrincipal();
			} else {
				throw new CustomAuthenticationException("Invalid session");
			}
		} else {
			throw new CustomAuthenticationException("Invalid session");
		}

	}

}
