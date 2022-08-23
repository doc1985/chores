package com.chorley.app.chorelyapp.orchastrators;

import java.security.Principal;

import com.chorley.app.chorelyapp.dtos.NewUserDto;
import com.chorley.app.chorelyapp.dtos.NewUserResponseDto;

public interface UserOrchastrator {
	public NewUserResponseDto createUser(Principal principal, NewUserDto model) throws Exception;
	public void deleteUser(long userId, Principal principal) throws Exception;
}
