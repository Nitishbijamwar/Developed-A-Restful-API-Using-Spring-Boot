package com.training.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UserDao implements IUserDao {
	public Map<UUID, UserData> USER = new HashMap<>();

	@Override
	public UUID addUser(UserData data) {

		USER.put(data.getId(), data);
		return data.getId();
	}

	@Override
	public List<UserData> getUsers() {
		return new ArrayList<>(USER.values());
	}

	@Override
	public void deleteUerbyId(UUID id) {
		USER.remove(id);

	}

	@Override
	public UserData getUserById(UUID id) {

		return USER.get(id);
	}

	@Override
	public void bulkDelete() {
		USER.remove(USER);

	}

	@Override
	public UUID putUser(UserData data) {
		USER.put(data.getId(), data);
		return null;
	}
	
	

	
	

	


	
	}
