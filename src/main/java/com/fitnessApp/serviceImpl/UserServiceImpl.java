package com.fitnessApp.serviceImpl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessApp.DAO.UsersDAO;
import com.fitnessApp.model.Users;
import com.fitnessApp.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersDAO userDAO;
	
	@Override
	public Users save(Users user) {
		
		return userDAO.save(user);
	}

	@Override
	public Users findByUserName(String username) {
		
		return userDAO.findById(username).orElse(null);
	}

}
