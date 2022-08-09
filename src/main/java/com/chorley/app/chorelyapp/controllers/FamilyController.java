package com.chorley.app.chorelyapp.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chorley.app.chorelyapp.dtos.authentication.SignupRequestDto;
import com.chorley.app.chorelyapp.services.FamilyService;

@RestController
@RequestMapping({"/api/families"})
public class FamilyController {
	
	@Autowired
	FamilyService familyService;
	
	@PostMapping() 
	public ResponseEntity<?> signupFamily(HttpServletRequest request, HttpServletResponse response, @RequestBody SignupRequestDto signupDto) throws Exception{
		familyService.signupFamily(request, response, signupDto);
		URI location = URI.create(request.getServletPath());
	    return ResponseEntity.created(location).build();
	}
}
