package com.chorley.app.chorelyapp.orchastrators;

import java.security.Principal;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chorley.app.chorelyapp.daos.FamilyDao;
import com.chorley.app.chorelyapp.daos.RewardDao;
import com.chorley.app.chorelyapp.dtos.NewRewardDto;
import com.chorley.app.chorelyapp.services.FamilyService;
import com.chorley.app.chorelyapp.services.RewardService;

@Service
public class RewardOrchastratorImpl implements RewardOrchastrator {

	@Autowired
	RewardService rewardService;
	
	@Autowired
	FamilyService familyService;
	
	@Override
	public void createReward(Principal principal, NewRewardDto model) {
		FamilyDao family = familyService.getFamilyByEmail(principal.getName());
		
		if (family == null) {
			throw new EntityNotFoundException();
		}

		rewardService.createReward(model, family);
	}

	@Override
	public void deleteReward(long rewardId, Principal principal) {
		FamilyDao family = familyService.getFamilyByEmail(principal.getName());
		
		if (family == null) {
			throw new EntityNotFoundException();
		}
		
		Optional<RewardDao> reward = rewardService.findOneByIdAndFamily(rewardId, family);
		
		rewardService.deleteReward(reward.orElseThrow(EntityNotFoundException::new));
	}

}
