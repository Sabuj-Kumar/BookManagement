package com.bookManagementSystem.service;

import com.bookManagementSystem.dto.JWTAuthResponse;
import com.bookManagementSystem.dto.RefreshTokenRequest;
import com.bookManagementSystem.dto.SignInRequest;
import com.bookManagementSystem.dto.SignUpRequest;
import com.bookManagementSystem.entities.User;
import com.bookManagementSystem.payload.ChangPassRequest;

public interface AuthenticationService {
	public User signUp(SignUpRequest signUpRequest);
	public JWTAuthResponse singIn(SignInRequest signInRequest);
	public JWTAuthResponse refreshToken(RefreshTokenRequest refreshToken);
	Boolean changePassword(ChangPassRequest changePassRequest);
}
