package com.training.app.auth;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.training.app.Context;
import com.training.app.User;

import java.util.List;

@RestController
@RequestMapping(value = "/api/authenticate")
public class AuthenticationRestController {

	@GetMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
	public User session() throws CustomAuthenticationException {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated()) {
			return (User) auth.getPrincipal();
		} else {
			throw new CustomAuthenticationException("Invalid session");
		}
	}

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
	public List<User> getUsers() throws CustomAuthenticationException {
		return Context.getUsersAsList();
	}
}
