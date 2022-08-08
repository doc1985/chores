package com.chorley.app.chorelyapp.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chorley.app.chorelyapp.daos.FamilyDao;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired 
	FamilyService familyService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		FamilyDao family = familyService.getFamilyByEmail(email);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		if (family != null) {
			return new User(family.getEmail(), family.getPassword(), authorities);
		} else {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
	}

}
