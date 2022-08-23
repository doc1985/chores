package com.chorley.app.chorelyapp.services;

import com.chorley.app.chorelyapp.daos.ChoreDao;
import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.dtos.NewChoreRequestDto;

public interface ChoreService {
	public void createChore(FamilyDao family, NewChoreRequestDto model) throws Exception;
	public ChoreDao getChoreByIdAndFamily(FamilyDao family, int choreId) throws Exception;
	public void deleteChore(ChoreDao chore) throws Exception;
}
