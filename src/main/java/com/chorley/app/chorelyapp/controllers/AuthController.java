package com.chorley.app.chorelyapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chorley.app.chorelyapp.dtos.AuthenticationRequestDto;
import com.chorley.app.chorelyapp.dtos.AuthenticationResponseDto;
import com.chorley.app.chorelyapp.services.JwtUserDetailsService;
import com.chorley.app.chorelyapp.utils.JwtTokenUtil;

@RestController
@RequestMapping({"/auth"})
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	JwtUserDetailsService userDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping({"/authenticate"})
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto authenticationRequest) throws Exception {
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());
		
		if (passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {

			final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
	
			return ResponseEntity.ok(new AuthenticationResponseDto(accessToken, refreshToken));
		}
		
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}

}
