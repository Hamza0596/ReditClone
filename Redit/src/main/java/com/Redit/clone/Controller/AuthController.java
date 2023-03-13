package com.Redit.clone.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Redit.clone.Dto.AuthenticationResponse;
import com.Redit.clone.Dto.LoginRequest;
import com.Redit.clone.Dto.RefreshTokenRequest;
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Service.RefreshTokenService;
import com.Redit.clone.Service.authService;

@RequestMapping("api/auth")
@RestController
public class AuthController {
	@Autowired
	authService authService;
	@Autowired
	RefreshTokenService refreshTokenService;
	

	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
		authService.signup(userDto);
		return new ResponseEntity<>( HttpStatus.OK);
		
	}
	
	@GetMapping("accountVerification/{token}")
	public  ResponseEntity<String> verifyAccount(@PathVariable String token){
		authService.verifyAccount(token) ;
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	@PostMapping("refresh/token")
	public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		return authService.refreshToken(refreshTokenRequest);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return new ResponseEntity<>("Refresh token deleted successfuly", HttpStatus.OK);
		
	}

}
