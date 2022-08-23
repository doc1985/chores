package com.chorley.app.chorelyapp.controllers;

import java.net.URI;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chorley.app.chorelyapp.dtos.NewChoreRequestDto;
import com.chorley.app.chorelyapp.orchastrators.ChoreOrchastrator;

@RestController
@RequestMapping({"api/chores"})
public class ChoreController {
	
	@Autowired
	ChoreOrchastrator choreOrchastrator;
	
	@PostMapping()
	public ResponseEntity<?> createChore(HttpServletRequest request, Principal principal, @RequestBody NewChoreRequestDto model) throws Exception {
		choreOrchastrator.createChore(principal, model);
	
		URI location = URI.create(request.getServletPath());
	    return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping({"/{choreId}"})
	public ResponseEntity<?> deleteChore(@PathVariable int choreId, Principal principal) throws Exception {
		choreOrchastrator.deleteChoreById(choreId, principal);
		
		return ResponseEntity.ok().build();
	}
}
