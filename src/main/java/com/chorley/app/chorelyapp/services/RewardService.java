package com.chorley.app.chorelyapp.services;

import java.util.Optional;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.daos.RewardDao;
import com.chorley.app.chorelyapp.dtos.NewRewardDto;

public interface RewardService {
	public void createReward(NewRewardDto model, FamilyDao family);
	public Optional<RewardDao> findOneByIdAndFamily(long id, FamilyDao family);
	public void deleteReward(RewardDao reward);
}
