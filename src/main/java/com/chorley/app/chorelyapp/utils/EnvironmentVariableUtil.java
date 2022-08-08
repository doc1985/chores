package com.chorley.app.chorelyapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentVariableUtil {

    private final String jwtSecret;

    @Autowired
    public EnvironmentVariableUtil(@Value("${secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

	public String getJwtSecret() {
		return jwtSecret;
	}
}
