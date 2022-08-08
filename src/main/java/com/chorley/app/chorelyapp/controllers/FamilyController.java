package com.chorley.app.chorelyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.dtos.NewFamilyDto;
import com.chorley.app.chorelyapp.services.FamilyService;

@RestController
@RequestMapping({"/families"})
public class FamilyController {
	
	@Autowired
	FamilyService familyService;
	
	@PostMapping() 
	public FamilyDao saveFamily(@RequestBody NewFamilyDto familyDto){
		return familyService.saveFamily(familyDto);
	}
}
