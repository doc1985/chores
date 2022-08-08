package com.chorley.app.chorelyapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.dtos.NewFamilyDto;
import com.chorley.app.chorelyapp.repositories.IFamilyRepository;

@Service
public class FamilyServiceImpl implements FamilyService{

	@Autowired IFamilyRepository familyRepository;
	@Autowired PasswordEncoder passwordEncoder;
	
	@Override
	public FamilyDao getFamilyByEmail(String email) {
		return familyRepository.findByEmail(email);
	}

	@Override
	public FamilyDao saveFamily(NewFamilyDto familyDto) {
		FamilyDao family = new FamilyDao();
		family.setEmail(familyDto.getEmail());
		family.setName(familyDto.getName());
		family.setPassword(passwordEncoder.encode(familyDto.getPassword()));
		
		return familyRepository.save(family);
	}
	
	

}
