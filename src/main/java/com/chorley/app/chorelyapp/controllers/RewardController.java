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

import com.chorley.app.chorelyapp.dtos.NewRewardDto;
import com.chorley.app.chorelyapp.orchastrators.RewardOrchastrator;

@RestController
@RequestMapping({"/api/rewards"})
public class RewardController {
	
	@Autowired
	RewardOrchastrator rewardOrchastrator;
	
	@PostMapping()
	public ResponseEntity<?> createReward(HttpServletRequest request, Principal principal, @RequestBody NewRewardDto model) throws Exception {
		rewardOrchastrator.createReward(principal, model);
	
		URI location = URI.create(request.getServletPath());
	    return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{rewardId}")
	public ResponseEntity<?> deleteUser(@PathVariable long rewardId, Principal principal) throws Exception {
		rewardOrchastrator.deleteReward(rewardId, principal);
		
		return ResponseEntity.ok().build();
	}
}