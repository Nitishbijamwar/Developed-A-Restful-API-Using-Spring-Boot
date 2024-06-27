package com.training.user;

import java.util.List;
import java.util.UUID;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.training.app.SpringBot;

@SpringBootTest(classes = SpringBot.class)
public class UserServiceTest1 {
	@Autowired
	private IUserService userService;

	@Test
	public void getUserTest() throws UserException {

		UserDto userdto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "Myusername1", "admin4567@info.com","123456");
		UserDto addedUser = userService.addUser(userdto);

		List<UserDto> userlist = userService.getUsers();

		for (UserDto user : userlist)
			if (user.getId().equals(userdto.getId())) {
				Assertions.assertEquals(userdto.getFirstName(), user.getFirstName());
			}
	}

	@Test
	public void getUserByIdTest() throws UserException {
		UserDto userdto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "Myusername", "admin456@info.com","123456");
		UserDto dto = this.userService.addUser(userdto);

		UserDto addeduser = this.userService.getUserById(dto.getId());

		Assertions.assertEquals(dto.getFirstName(), addeduser.getFirstName());
		Assertions.assertEquals(dto.getLastName(), addeduser.getLastName());
	}

	@Test
	public void createUserTest() throws UserException {
		UserDto userdto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "username", "admin123@info.com","123456");
		UserDto dto = this.userService.addUser(userdto);

		UserDto addeduser = this.userService.getUserById(dto.getId());

		Assertions.assertEquals(dto.getFirstName(), addeduser.getFirstName());
		Assertions.assertEquals(dto.getLastName(), addeduser.getLastName());
	}

	@Test
	public void createUserWithfirstNameEmpty() throws UserException {
		UserDto dto = new UserDto(UUID.randomUUID(), "", "lastname", "username1", "admin1@info.com","123456");

		try {
			UserDto createduser = userService.addUser(dto);
			Assertions.assertEquals(dto.getLastName(), createduser.getLastName());
		} catch (UserException exception) {
			Assertions.assertEquals("First Name  Can not be Empty or Null", exception.getMessage());
		}
	}

	@Test
	public void createUserWithLastNameEmpty() throws UserException {
		UserDto dto = new UserDto(UUID.randomUUID(), "firstname", null, "username2", "admin2@info.com","123456");

		try {
			UserDto createduser = userService.addUser(dto);
			Assertions.assertEquals(dto.getLastName(), createduser.getLastName());
		} catch (UserException exception) {
			Assertions.assertEquals("Last Name  Can not be Empty or Null ", exception.getMessage());
		}
	}

	@Test
	public void createUserWithUserNameEmpty() throws UserException {
		UserDto dto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "", "admin3@info.com","123456");

		try {
			UserDto createduser = userService.addUser(dto);
			Assertions.assertEquals(dto.getLastName(), createduser.getLastName());
		} catch (UserException exception) {
			Assertions.assertEquals("User Name Can not be Empty or Null", exception.getMessage());
		}
	}

	@Test
	public void createUserWithEmptymail() throws UserException {
		UserDto dto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "username3", null,"123456");

		try {
			UserDto createduser = userService.addUser(dto);
			Assertions.assertEquals(dto.getLastName(), createduser.getLastName());
		} catch (UserException exception) {
			Assertions.assertEquals("Email Can not be Empty", exception.getMessage());
		}
	}

	@Test
	public void createUserWithIncorrectEmail() throws UserException {
		UserDto dto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "username3", "admin@info","123456");

		try {
			UserDto createduser = userService.addUser(dto);
			Assertions.assertEquals(dto.getLastName(), createduser.getLastName());
		} catch (UserException exception) {
			Assertions.assertEquals("Email  syntax is incorrect", exception.getMessage());
		}
	}

	@Test
	public void createUserWithAlredyExistEmail() throws UserException {
		UserDto dto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "username4", "admin789@info.com","123456");
		UserDto createduser = userService.addUser(dto);
		Assertions.assertEquals(dto.getLastName(), createduser.getLastName());
		UserDto dto2 = new UserDto(UUID.randomUUID(), "firstname", "lastname", "username5", "admin789@info.com","123456");

		try {
			UserDto createdSecondUser = userService.addUser(dto2);
			Assertions.assertEquals(dto2.getLastName(), createdSecondUser.getLastName());
		} catch (UserException exception) {
			Assertions.assertEquals("Email  already Exist", exception.getMessage());
		}
	}

	@Test
	public void createUserWithAlredyExistUserName() throws UserException {
		UserDto dto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "username6", "admin567@info.com","123456");

		UserDto createduser = userService.addUser(dto);
		Assertions.assertEquals(dto.getLastName(), createduser.getLastName());
		UserDto dto2 = new UserDto(UUID.randomUUID(), "firstname", "lastname", "username6", "admin@info.com","123456");

		try {
			UserDto createdSecondUser = userService.addUser(dto2);
			Assertions.assertEquals(dto2.getLastName(), createdSecondUser.getLastName());
		} catch (UserException exception) {
			Assertions.assertEquals("UserName  already Exist", exception.getMessage());

		}
	}

	@Test
	public void deleteUserTest() throws UserException {
		UserDto userdto = new UserDto(UUID.randomUUID(), "firstname", "lastname", "username7", "admin4@info.com","123456");
		UserDto dto = this.userService.addUser(userdto);

		UserDto addeduser = this.userService.getUserById(dto.getId());

		Assertions.assertEquals(dto.getFirstName(), addeduser.getFirstName());
		Assertions.assertEquals(dto.getLastName(), addeduser.getLastName());

		userService.deleteUserById(dto.getId());
		try {
			UserDto deleteduser = this.userService.getUserById(dto.getId());
		} catch (UserException e) {
			Assertions.assertEquals("User does not  Exist", e.getMessage());

		}

	}