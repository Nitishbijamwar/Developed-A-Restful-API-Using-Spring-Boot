package com.training.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {
	public static final Map<UUID, UserDto> USER = new HashMap<>();

	@Autowired
	private IUserDao userdao;

	@Override
	public UserDto addUser(UserDto dto) throws UserException {
		dto = populateUserData();
		if (dto.getFirstname() == null || dto.getFirstname().isEmpty()) {
			throw new UserException("first Name Can not be Empty or null");
		}
		if (dto.getFirstname() == null || dto.getFirstname().length()>20) {
			throw new UserException("first Name Can not be greater than 20 character");
		}
		if (dto.getLastname() == null || dto.getLastname().isEmpty()) {
			throw new UserException("Last Name Can not be Empty or null");
		}
		if (dto.getLastname() == null || dto.getLastname().length()>20) {
			throw new UserException("last Name Can not be greater than 20 character");
		}

		if (dto.getUsername() == null || dto.getUsername().trim().isEmpty()) {
			throw new UserException("User Name Can not be Empty");
		}
		if (dto.getUsername() == null || dto.getUsername().length()>20) {
			throw new UserException("user Name Can not be greater than 20 character");
		}

		if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
			throw new UserException("Email Can not be Empty");
		}
		if (!EmailValidator.isValidEmail(dto.getEmail())) {
			throw new UserException("Email format is wrong");
		}
		for (UserData user : userdao.getUsers()) {
			if (user.getEmail().equals(dto.getEmail())) {
				throw new UserException("Email already Exist");
			}
			if (user.getUsername().equals(dto.getUsername())) {
				throw new UserException("Username is already Exist");
			}

		}

		dto.setId(UUID.randomUUID());
		userdao.addUser(fromDtoToData(dto));
		return dto;

	}

	@Override

	public List<UserDto> getUsers() {

		List<UserDto> userlist = new ArrayList<>();

		userdao.getUsers().forEach(data -> userlist.add(fromDataToDto(data)));

		return userlist;

	}

	@Override

	public UserDto getUserById(UUID id) throws UserException {
		for (UserData user : userdao.getUsers()) {
			if (!user.getId().equals(id)) {
				throw new UserException("userdoes not  Exist");
			}
		}
		return fromDataToDto(userdao.getUserById(id));

	}

	@Override

	public void deleteUserById(UUID id) throws UserException {
		for (UserData user : userdao.getUsers()) {
			if (!user.getId().equals(id)) {
				throw new UserException("userdoes not  Exist");
			}
		}

		userdao.deleteUerbyId(id);

	}

	@Override

	public UUID updateUser(UserDto dto) throws UserException {
		for (UserData user : userdao.getUsers()) {
			if (!user.getId().equals(dto.getId())) {
				throw new UserException("userdoes not  Exist");
			}
		}

		return userdao.putUser(fromDtoToData(dto));

	}

	

	private UserDto fromDataToDto(UserData userdata) {
		UserDto dto = new UserDto();
		dto.setId(userdata.getId());
		dto.setFirstname(userdata.getFirstname());
		dto.setLastname(userdata.getLastname());
		dto.setUsername(userdata.getUsername());
		dto.setEmail(userdata.getEmail());

		return dto;
	}

	private UserData fromDtoToData(UserDto dto) {
		UserData data = new UserData();
		data.setId(dto.getId());
		data.setFirstname(dto.getFirstname());
		data.setLastname(dto.getLastname());
		data.setUsername(dto.getUsername());
		data.setEmail(dto.getEmail());
		return data;
	}
	public UserDto populateUserData() {
		UserDto dto = new UserDto ();
		dto.setFirstname("mnjuhjuyadsfghmnjhuytsd");
		dto.setLastname("bijamwarwsgheatdghh");
		dto.setEmail("info@gmail.com");
		dto.setUsername("admin");
		return dto;
	}

	

}
