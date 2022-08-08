package com.chorley.app.chorelyapp.dtos;

import java.io.Serializable;

public class AuthenticationResponseDto implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String accessToken;
	private final String refreshToken;

	public AuthenticationResponseDto(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
	
	

}

