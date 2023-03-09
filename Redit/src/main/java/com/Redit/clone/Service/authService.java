package com.Redit.clone.Service;


import com.Redit.clone.Dto.AuthenticationResponse;
import com.Redit.clone.Dto.LoginRequest;
import com.Redit.clone.Dto.RefreshTokenRequest;
import com.Redit.clone.Dto.UserDto;

public interface authService {

	public void signup(UserDto userDto);
	public void verifyAccount(String token);
	public AuthenticationResponse login(LoginRequest loginRequest);
	public AuthenticationResponse refreshToken( RefreshTokenRequest refreshTokenRequest);
}
