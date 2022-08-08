package com.chorley.app.chorelyapp.services;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.dtos.NewFamilyDto;

public interface FamilyService {
	public FamilyDao getFamilyByEmail(String email);
	
	public FamilyDao saveFamily(NewFamilyDto familyDto);
}
