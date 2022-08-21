package com.chorley.app.chorelyapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.daos.UserDao;
import com.chorley.app.chorelyapp.dtos.NewUserDto;
import com.chorley.app.chorelyapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDao createUser(NewUserDto model, FamilyDao family) {
		UserDao user = new UserDao();
		user.setFirstName(model.getFirstName());
		user.setFamily(family);
		
		return userRepository.save(user);
	}

}
