package com.chorley.app.chorelyapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.daos.RewardDao;
import com.chorley.app.chorelyapp.dtos.NewRewardDto;
import com.chorley.app.chorelyapp.repositories.RewardRepository;

@Service
public class RewardServiceImpl implements RewardService {

	@Autowired
	RewardRepository rewardRepository;
	
	@Override
	public void createReward(NewRewardDto model, FamilyDao family) {
		RewardDao reward = new RewardDao();
		reward.setCost(model.getCost());
		reward.setFamily(family);
		reward.setName(model.getName());
		
		rewardRepository.save(reward);

	}

	@Override
	public Optional<RewardDao> findOneByIdAndFamily(long rewardId, FamilyDao family) {
		return rewardRepository.findByFamilyIdAndId(family.getId(), rewardId);
	}

	@Override
	public void deleteReward(RewardDao reward) {
		rewardRepository.delete(reward);
	}

}
