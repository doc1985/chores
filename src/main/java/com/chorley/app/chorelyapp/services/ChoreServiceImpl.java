package com.chorley.app.chorelyapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chorley.app.chorelyapp.daos.ChoreDao;
import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.dtos.NewChoreRequestDto;
import com.chorley.app.chorelyapp.repositories.ChoreRepository;

@Service
public class ChoreServiceImpl implements ChoreService {

	@Autowired
	ChoreRepository choreRepository;
	
	@Override
	public void createChore(FamilyDao family, NewChoreRequestDto model) throws Exception {
		ChoreDao chore = new ChoreDao();
		chore.setFamily(family);
		chore.setName(model.getName());
		chore.setValue(model.getValue());
		
		choreRepository.save(chore);

	}

}
