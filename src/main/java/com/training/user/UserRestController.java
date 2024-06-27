package com.training.user;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/users")
@RestController

public class UserRestController extends AController {
	@Autowired
	private IUserService userservice;

	@GetMapping
	
	public List<UserDto> getUser() {
		return userservice.getUsers();

	}

	@GetMapping(value = "/{id}")
	public UserDto getuser(@PathVariable UUID id) throws UserException {
		return userservice.getUserById(id);

	}

	@PostMapping
	public void postUser(@RequestBody UserDto dto) throws UserException {
		userservice.addUser(dto);

	}

	@DeleteMapping(value = "/{id}")
	public void deleteUserById(@PathVariable UUID id) throws UserException {
		userservice.deleteUserById(id);
	}
	
	@PutMapping
	public void updateUser(@RequestBody UserDto dto) throws UserException {
		userservice.updateUser(dto);

	}

}
