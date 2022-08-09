package com.chorley.app.chorelyapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.daos.UserDao;
import com.chorley.app.chorelyapp.dtos.authentication.SignupRequestDto;
import com.chorley.app.chorelyapp.repositories.FamilyRepository;
import com.chorley.app.chorelyapp.repositories.UserRepository;
import com.chorley.app.chorelyapp.utils.EnvironmentVariableUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FamilyServiceImpl implements FamilyService{

	@Autowired FamilyRepository familyRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired UserRepository userRepository;
	@Autowired EnvironmentVariableUtil environmentVariableUtil;
	
	@Override
	public FamilyDao getFamilyByEmail(String email) {
		return familyRepository.findByEmail(email);
	}

	@Override
	public void signupFamily(HttpServletRequest request, 
			HttpServletResponse response, 
			SignupRequestDto signupDto) throws Exception{
		FamilyDao existingFamily = getFamilyByEmail(signupDto.getFamily().getEmail());
		
		if (existingFamily != null) {
			throw new EntityExistsException("Email is unavailable");
		}
		
		FamilyDao family = new FamilyDao();
		family.setEmail(signupDto.getFamily().getEmail());
		family.setName(signupDto.getFamily().getName());
		family.setPassword(passwordEncoder.encode(signupDto.getFamily().getPassword()));
		
		FamilyDao newFamily = familyRepository.save(family);
		
		UserDao user = new UserDao();
		user.setFirstName(signupDto.getUser().getFirstName());
		user.setFamily(newFamily);
		
		userRepository.save(user);
		
		Algorithm algorithm = Algorithm.HMAC256(environmentVariableUtil.getJwtSecret().getBytes());
		Date access_expiration = new Date(System.currentTimeMillis() + 1 * 1000 * 60 * 60);
		Date refresh_expiration = new Date(System.currentTimeMillis() + 1 * 1000 * 60 * 60 * 24 * 14);

		String accessToken = JWT.create().withSubject(newFamily.getEmail()).withExpiresAt(access_expiration)
				.withIssuer(request.getRequestURL().toString()).withClaim("roles", new ArrayList<>()).sign(algorithm);

		String refreshToken = JWT.create().withSubject(newFamily.getEmail()).withExpiresAt(refresh_expiration)
				.withIssuer(request.getRequestURL().toString()).sign(algorithm);

		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", accessToken);
		tokens.put("refresh_token", refreshToken);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(201);

		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

}
