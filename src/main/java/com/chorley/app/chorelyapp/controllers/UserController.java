package com.chorley.app.chorelyapp.controllers;

import java.net.URI;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chorley.app.chorelyapp.dtos.NewUserDto;
import com.chorley.app.chorelyapp.orchastrators.UserOrchastrator;

@RestController
@RequestMapping({"/api/users"})
public class UserController {
	
	@Autowired
	UserOrchastrator userOrchastrator;
	
	@PostMapping()
	public ResponseEntity<?> createUser(HttpServletRequest request, Principal principal, @RequestBody NewUserDto model) throws Exception {
		userOrchastrator.createUser(principal, model);
	
		URI location = URI.create(request.getServletPath());
	    return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable long userId, Principal principal) throws Exception {
		userOrchastrator.deleteUser(userId, principal);
		
		return ResponseEntity.ok().build();
	}
}
