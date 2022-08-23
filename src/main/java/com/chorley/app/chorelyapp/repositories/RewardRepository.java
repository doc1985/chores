package com.chorley.app.chorelyapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chorley.app.chorelyapp.daos.RewardDao;

public interface RewardRepository extends JpaRepository<RewardDao, Long> {
	public Optional<RewardDao> findByFamilyIdAndId(long familyId, long id);
}
