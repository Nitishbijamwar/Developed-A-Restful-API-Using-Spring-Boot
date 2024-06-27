package com.training.user;

import java.util.List;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public interface IUserDao {
List<UserData> getUsers();
	


UserData getUserById(UUID id);


UUID addUser(UserData data);



UUID putUser(UserData data);


void deleteUerbyId(UUID id);


void bulkDelete();





}
