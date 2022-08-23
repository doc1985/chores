package com.chorley.app.chorelyapp.orchastrators;

import java.security.Principal;

import com.chorley.app.chorelyapp.dtos.NewRewardDto;

public interface RewardOrchastrator {
	public void createReward(Principal principal, NewRewardDto model);
	public void deleteReward(long rewardId, Principal principal);
}
