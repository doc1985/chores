package com.chorley.app.chorelyapp.services;


import javax.persistence.EntityNotFoundException;

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

	@Override
	public ChoreDao getChoreByIdAndFamily(FamilyDao family, int choreId) throws Exception {
		ChoreDao chore = choreRepository.findByFamilyIdAndId(family.getId(), choreId);
		
		if (chore == null) {
			throw new EntityNotFoundException();
		}
		
		return chore;
	}

	@Override
	public void deleteChore(ChoreDao chore) throws Exception {
		choreRepository.delete(chore);
	}

}
