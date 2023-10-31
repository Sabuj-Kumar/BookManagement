package com.bookManagementSystem.service.impl;

import java.util.Date;
import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookManagementSystem.dto.JWTAuthResponse;
import com.bookManagementSystem.dto.RefreshTokenRequest;
import com.bookManagementSystem.dto.SignInRequest;
import com.bookManagementSystem.dto.SignUpRequest;
import com.bookManagementSystem.entities.User;
import com.bookManagementSystem.enums.Role;
import com.bookManagementSystem.payload.ChangPassRequest;
import com.bookManagementSystem.repository.UserRepository;
import com.bookManagementSystem.service.AuthenticationService;
import com.bookManagementSystem.service.JWTService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncode;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	@Override
	public User signUp(SignUpRequest signUpRequest) {
		User user = this.modelMapper.map(signUpRequest, User.class);
		
		user.setPassword(this.passwordEncode.encode(signUpRequest.getPassword()));
		user.setCreatedDate(new Date());
		if(user.getRole() == null) {
			user.setRole(Role.USER);
		}
		
	  return this.userRepo.save(user);
	}

	@Override
	public JWTAuthResponse singIn(SignInRequest signInRequest) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));         
		
		User user = userRepo.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid user and password"));
		
		String token = this.jwtService.generateToken(user);
		String refreshToken = this.jwtService.generateRefreshToken(new HashMap<>(), user);
		
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		
		jwtAuthResponse.setToken(token);
		jwtAuthResponse.setRefreshToken(refreshToken);
		
		return jwtAuthResponse;
	} 
	
	@Override
	public JWTAuthResponse refreshToken(RefreshTokenRequest refreshToken) {
		
		String userEmail = this.jwtService.getUserName(refreshToken.getToken());
		
		User user = this.userRepo.findByEmail(userEmail).orElseThrow();
		
		if(this.jwtService.validateToken(refreshToken.getToken(), user)) {
			
			String token = this.jwtService.generateToken(user);
			
			JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
			
			jwtAuthResponse.setToken(token);
			jwtAuthResponse.setRefreshToken(refreshToken.getToken());
			
			return jwtAuthResponse;
		}
		return null;
	}

	@Override
	public Boolean changePassword(ChangPassRequest changePassRequest) {
		
        if(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(changePassRequest.getClass(),changePassRequest.getPrePassword())).isAuthenticated()) {
        	User user = userRepo.findByEmail(changePassRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid user and password"));
        	
        	user.setPassword(this.passwordEncode.encode(changePassRequest.getNewPassword()));
        	
        	userRepo.save(user);
        	
        	return true;
        }
        
		return false;
	}
}
