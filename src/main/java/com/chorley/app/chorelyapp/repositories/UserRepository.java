package com.chorley.app.chorelyapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chorley.app.chorelyapp.daos.UserDao;

public interface UserRepository extends JpaRepository<UserDao, Long> {

}
