package com.fitnessApp.service;

import com.fitnessApp.model.Users;

public interface UserService {

	Users save(Users userEntity);

	Users findByUserName(String username);

}
