package com.chorley.app.chorelyapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.services.FamilyService;
import com.chorley.app.chorelyapp.utils.EnvironmentVariableUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping({"/api/auth"})
public class AuthController {
	
	@Autowired
	FamilyService familyService;
	
	@Autowired
	EnvironmentVariableUtil environmentVariableUtil;
	
	@GetMapping({"/token/refresh"})
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		final String requestTokenHeader = request.getHeader("Authorization");

		String email = null;
		String refreshToken = null;
		
		
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			refreshToken = requestTokenHeader.substring(7);
			try {
				Algorithm algorithm = Algorithm.HMAC256(environmentVariableUtil.getJwtSecret().getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decoded = verifier.verify(refreshToken);
				
				email = decoded.getSubject();
				FamilyDao family = familyService.getFamilyByEmail(email);
				Date access_expiration = new Date(System.currentTimeMillis() + 1 * 1000 * 60 * 60);
				
				String accessToken = JWT.create().withSubject(family.getEmail()).withExpiresAt(access_expiration)
						.withIssuer(request.getRequestURL().toString()).withClaim("roles", new ArrayList<>()).sign(algorithm);

				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", accessToken);
				tokens.put("refresh_token", refreshToken);
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);

				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (Exception e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			throw new RuntimeException("token is missing");
		}
	}

}
