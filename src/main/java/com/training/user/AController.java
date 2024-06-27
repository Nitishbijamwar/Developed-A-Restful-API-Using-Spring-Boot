package com.training.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class AController {
	@ExceptionHandler(UserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	
	public String userExceptionHandller(UserException e) {
		return e.getMessage();
	}
	
}
