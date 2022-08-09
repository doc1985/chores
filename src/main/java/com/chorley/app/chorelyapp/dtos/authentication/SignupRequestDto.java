package com.chorley.app.chorelyapp.dtos.authentication;

import com.chorley.app.chorelyapp.dtos.NewFamilyDto;
import com.chorley.app.chorelyapp.dtos.NewUserDto;

public class SignupRequestDto {
	private NewFamilyDto family;
	private NewUserDto user;

	public NewFamilyDto getFamily() {
		return family;
	}

	public NewUserDto getUser() {
		return user;
	}

	public void setUser(NewUserDto user) {
		this.user = user;
	}

	public void setFamily(NewFamilyDto family) {
		this.family = family;
	}
}
