package com.chorley.app.chorelyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chorley.app.chorelyapp.daos.FamilyDao;

public interface FamilyRepository extends JpaRepository<FamilyDao, Long> {
	FamilyDao findByEmail(String email);
}
