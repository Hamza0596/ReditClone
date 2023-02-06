package com.Redit.clone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
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
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Model.User;
import com.Redit.clone.Repository.UserRepo;
import com.Redit.clone.Service.authService;

@RequestMapping("api/auth")
@RestController
public class AuthController {
	@Autowired
	authService authService;
	
	

	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
		authService.signup(userDto);
		return new ResponseEntity("UserRegistrationSuccesfuly", HttpStatus.OK);
		
	}
	
	@GetMapping("accountVerification/{token}")
	public  ResponseEntity<String> verifyAccount(@PathVariable String token){
		authService.verifyAccount(token) ;
		return new ResponseEntity<>("Account activated successfuly",HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}

}
