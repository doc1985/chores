package com.chorley.app.chorelyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chorley.app.chorelyapp.daos.ChoreDao;

@Repository
public interface ChoreRepository extends JpaRepository<ChoreDao, Long> {

}
