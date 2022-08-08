package com.chorley.app.chorelyapp.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chorley.app.chorelyapp.utils.EnvironmentVariableUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final String APPLICTION_JSON_VALUE = "application/json";
	private AuthenticationManager authenticationManager;
	private String secret;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
		Date expired = new Date(System.currentTimeMillis() + 1 * 60 * 1000 * 60 * 24 * 14);

		String accessToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(expired)
				.withIssuer(request.getRequestURL().toString()).withClaim("roles", new ArrayList<>()).sign(algorithm);

		String refreshToken = JWT.create().withSubject(user.getUsername()).withExpiresAt(expired)
				.withIssuer(request.getRequestURL().toString()).sign(algorithm);

		// response.setHeader("access_token", accessToken);
		// response.setHeader("refresh_token", refreshToken);

		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", accessToken);
		tokens.put("refresh_token", refreshToken);
		response.setContentType(APPLICTION_JSON_VALUE);

		new ObjectMapper().writeValue(response.getOutputStream(), tokens);

	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
