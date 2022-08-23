package com.chorley.app.chorelyapp.services;

import java.util.Optional;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.daos.UserDao;
import com.chorley.app.chorelyapp.dtos.NewUserDto;

public interface UserService {
	public UserDao createUser(NewUserDto model, FamilyDao family);
	public Optional<UserDao> findOneByIdAndFamilyId(long userId, long familyId);
	public void deleteUser(UserDao user);
}
