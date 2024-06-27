package com.training.user;

import java.util.List;
import java.util.UUID;

public interface IUserService {
	List<UserDto> getUsers();
	
	UserDto addUser(UserDto dto) throws UserException;
	
	UserDto getUserById(UUID id) throws UserException;

	void deleteUserById(UUID id) throws UserException;
	
	

	

	UUID updateUser(UserDto dto) throws UserException;

	

}
