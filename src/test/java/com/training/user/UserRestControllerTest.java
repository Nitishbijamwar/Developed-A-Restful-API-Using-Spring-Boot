package com.training.user;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.training.app.SpringBot;

@SpringBootTest(classes = SpringBot.class)

public class UserRestControllerTest {

	private static final String URL_API = "/api/v1/users";
	@Mock
	private UserService userservice;
	@InjectMocks
	private UserRestController usercontroller;
	private MockMvc mockMvc;
	private UserDto savedUserDto;

	@BeforeEach

	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(usercontroller).build();
		savedUserDto = new UserDto();
		savedUserDto.setId(UUID.randomUUID());
		savedUserDto.setFirstname("firstname");
		savedUserDto.setLastname("lastname");
		savedUserDto.setUsername("username");
		savedUserDto.setEmail("info@gamail.com");
	}

	@Test
	public void addUserTest() throws UserException {
		UserDto dto = new UserDto();
		dto.setId(UUID.randomUUID());
		dto.setFirstname("Nitish");
		dto.setLastname("Bijamwar");
		dto.setUsername("nitishbijamwar");
		dto.setEmail("nitish@gmail.com");
		when(userservice.addUser(dto)).thenReturn(savedUserDto);
		when(userservice.getUserById(dto.getId())).thenReturn(dto);
		UserDto singleuser = userservice.getUserById(dto.getId());

		Assertions.assertEquals(dto.getFirstname(), singleuser.getFirstname());
		Assertions.assertEquals(dto.getLastname(), singleuser.getLastname());
		Assertions.assertEquals(dto.getUsername(), singleuser.getUsername());
		Assertions.assertEquals(dto.getEmail(), singleuser.getEmail());
	}

	@Test
	public void getUserTest() throws Exception {
		UserDto dto = new UserDto();
		dto.setId(UUID.randomUUID());
		dto.setFirstname("Nitish");
		dto.setLastname("Bijamwar");
		dto.setUsername("nitishbijamwar");
		dto.setEmail("nitish@gmail.com");

		List<UserDto> users = Arrays.asList(dto);
		when(userservice.getUsers()).thenReturn(users);

		when(userservice.addUser(dto)).thenReturn(savedUserDto);
		List<UserDto> userlist = getUser(dto);
		Assertions.assertEquals(1, userlist.size());
		Assertions.assertEquals(dto.getFirstname(), userlist.get(0).getFirstname());
		Assertions.assertEquals(dto.getLastname(), userlist.get(0).getLastname());
		Assertions.assertEquals(dto.getUsername(), userlist.get(0).getUsername());
		Assertions.assertEquals(dto.getEmail(), userlist.get(0).getEmail());
	}

	private List<UserDto> getUser(UserDto dto) {

		return userservice.getUsers();
	}
	@Test
	public void getUserByIdTest() throws UserException {
		UserDto dto = new UserDto();
		UUID userid = UUID.randomUUID();
		dto.setFirstname("Nitish");
		dto.setLastname("Bijamwar");
		dto.setUsername("nitishbijamwar");
		dto.setEmail("nitish@gmail.com");
		when(userservice.getUserById(userid)).thenReturn(dto);
		
		UserDto user=userservice.getUserById(userid);
		Assertions.assertEquals(dto.getFirstname(), user.getFirstname());
		Assertions.assertEquals(dto.getLastname(), user.getLastname());
		Assertions.assertEquals(dto.getUsername(), user.getUsername());
		Assertions.assertEquals(dto.getEmail(), user.getEmail());
		
	}
	
	@Test
	public void deleteUserByIdTest() throws Exception{
		UserDto dto = new UserDto();
		UUID userid = UUID.randomUUID();
		dto.setId(userid);
		dto.setFirstname("nitish");
		dto.setLastname("bijamwar");
		dto.setUsername("username");
		dto.setEmail("abc@gmail.com");
		when(userservice.getUserById(userid)).thenReturn(dto);
		UserDto user = userservice.getUserById(userid);
		userservice.deleteUserById(userid);
		when(userservice.getUserById(userid)).thenReturn(null);
		UserDto user1 = userservice.getUserById(userid);
		Assertions.assertNull(user1);
	}
	
	@Test
	public void addUserWithEmptyFirstName() throws Exception{
		UserDto dto = new UserDto();
		dto.setFirstname("");
		dto.setLastname("bijamwar");
		dto.setUsername("username");
		dto.setEmail("abc@gmail.com");
		when(userservice.addUser(dto)).thenThrow(new UserException("firstName cannot be empty"));
		
		try {
			UserDto user = userservice.addUser(dto);
		}
		catch(UserException exception) {
			Assertions.assertEquals("firstName cannot be empty", exception.getMessage());
		}
	}
	@Test
	public void addUserWithEmptyLastName() throws Exception{
		UserDto dto = new UserDto();
		dto.setFirstname("nitish");
		dto.setLastname("");
		dto.setUsername("username");
		dto.setEmail("abc@gmail.com");
		when(userservice.addUser(dto)).thenThrow(new UserException("lastName cannot be empty"));
		
		try {
			UserDto user = userservice.addUser(dto);
		}
		catch(UserException exception) {
			Assertions.assertEquals("lastName cannot be empty", exception.getMessage());
		}
	}
}
