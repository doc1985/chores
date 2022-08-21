package com.chorley.app.chorelyapp.orchastrators;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.daos.UserDao;
import com.chorley.app.chorelyapp.dtos.NewUserDto;
import com.chorley.app.chorelyapp.dtos.NewUserResponseDto;
import com.chorley.app.chorelyapp.services.FamilyService;
import com.chorley.app.chorelyapp.services.UserService;
import com.google.common.base.Strings;

@Service
public class UserOrchastratorImpl implements UserOrchastrator {

	@Autowired
	UserService userService; 
	
	@Autowired
	FamilyService familyService;
	
	@Override
	public NewUserResponseDto createUser(Principal principal, NewUserDto model) throws Exception{
		if(Strings.isNullOrEmpty(model.getFirstName())) {
			throw new IllegalArgumentException("First name is required");
		}
		
		FamilyDao family = familyService.getFamilyByEmail(principal.getName());
		
		UserDao user = userService.createUser(model, family);
		
		return new NewUserResponseDto(user.getId(), user.getFirstName());
	}

}
