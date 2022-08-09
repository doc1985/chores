package com.chorley.app.chorelyapp.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.dtos.authentication.SignupRequestDto;

public interface FamilyService {
	public FamilyDao getFamilyByEmail(String email);
	
	public void signupFamily(HttpServletRequest request, HttpServletResponse response, SignupRequestDto signupDto) throws Exception;
}
