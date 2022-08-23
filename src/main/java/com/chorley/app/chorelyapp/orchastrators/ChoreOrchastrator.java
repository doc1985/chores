package com.chorley.app.chorelyapp.orchastrators;

import java.security.Principal;

import com.chorley.app.chorelyapp.dtos.NewChoreRequestDto;

public interface ChoreOrchastrator {
	public void createChore(Principal principal, NewChoreRequestDto model) throws Exception;
	public void deleteChoreById(int choreId, Principal principal) throws Exception;
}
