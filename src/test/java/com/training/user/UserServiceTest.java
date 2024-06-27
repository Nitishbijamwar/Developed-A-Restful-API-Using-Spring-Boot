package com.training.user;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.app.SpringBot;
import com.training.app.User;

@SpringBootTest(classes = SpringBot.class)
public class UserServiceTest {
	@Autowired
	private IUserService userservice;

	@Test
	void getUserTest() {
		this.userservice.getUsers();
	}

	public UserDto populateUserData() {
		UserDto dto = new UserDto();
		dto.setFirstname("mnjuhjuyadsfghmnjh");
		dto.setLastname("bijamwarwsgheatd");
		dto.setEmail("info@gmail.com");
		dto.setUsername("admin");
		return dto;
	}

	@Test
	public void firstNameNotEmpty() throws UserException {
		UserDto dto = populateUserData();
		if (dto.getFirstname() == null || dto.getFirstname().isEmpty()) {
			throw new UserException("first Name Can not be Empty or null");
		}

	}

	@Test
	public void lastNameNotEmpty() throws UserException {
		UserDto dto = populateUserData();
		if (dto.getLastname() == null || dto.getLastname().isEmpty()) {
			throw new UserException("last Name Can not be Empty or null");
		}

	}
	@Test
	public void firstNameMaxChar() throws UserException {
		UserDto dto = populateUserData();
		if (dto.getFirstname() == null || dto.getFirstname().length() > 20) {
			throw new UserException("first Name Can not be greater than 20 character");
		}
		try {
			dto = userservice.addUser(dto);
		} catch (UserException exception) {
			Assertions.assertEquals("first Name Can not be greater than 20 character", exception.getMessage());

		}

	}

	@Test
	public void userNameNotEmpty() throws UserException {
		UserDto dto = populateUserData();
		if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
			throw new UserException("user Name Can not be Empty or null");
		}

	}
	@Test
	public void userNameMaxChar() throws UserException {
		UserDto dto = populateUserData();
		if (dto.getUsername() == null || dto.getUsername().length() > 20) {
			throw new UserException("user Name Can not be greater than 20 character");
		}
		try {
			dto = userservice.addUser(dto);
		} catch (UserException exception) {
			Assertions.assertEquals("user Name Can not be greater than 20 character", exception.getMessage());

		}

	}
	@Test
	public void lastNameMaxChar() throws UserException {
		UserDto dto = populateUserData();
		if (dto.getLastname() == null || dto.getLastname().length() > 20) {
			throw new UserException("Last name cannot be greater than 20 characters");
		}
		try {
			dto = userservice.addUser(dto);
		} catch (UserException exception) {
			Assertions.assertEquals("Last name cannot be greater than 20 characters", exception.getMessage());

		}

	}
	@Test
	public void emailNotEmpty() throws UserException{
		UserDto dto = populateUserData();
		if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
			throw new UserException("Email Can not be Empty");
		}
		

	}
	public void emailFormatIsWrong() throws UserException{
		UserDto dto = populateUserData();
		if (!EmailValidator.isValidEmail(dto.getEmail())) {
			throw new UserException("Email format is wrong");
		}	
	}
	
}
