package com.chorley.app.chorelyapp.orchastrators;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chorley.app.chorelyapp.daos.ChoreDao;
import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.dtos.NewChoreRequestDto;
import com.chorley.app.chorelyapp.services.ChoreService;
import com.chorley.app.chorelyapp.services.FamilyService;

@Service
public class ChoreOrchastratorImpl implements ChoreOrchastrator {

	@Autowired
	ChoreService choreService;
	
	@Autowired
	FamilyService familyService;
	
	@Override
	public void createChore(Principal principal, NewChoreRequestDto model) throws Exception {
		
		FamilyDao family = familyService.getFamilyByEmail(principal.getName());
		choreService.createChore(family, model);
	}

	@Override
	public void deleteChoreById(int choreId, Principal principal) throws Exception {
		FamilyDao family = familyService.getFamilyByEmail(principal.getName());
		ChoreDao chore = choreService.getChoreByIdAndFamily(family, choreId);
		choreService.deleteChore(chore);
		
	}

}
