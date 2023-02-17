package com.Redit.clone.Service.ServiceImpl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.AuthenticationResponse;
import com.Redit.clone.Dto.LoginRequest;
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Exceptions.SpringRedditException;
import com.Redit.clone.Model.NotificationEmail;
import com.Redit.clone.Model.User;
import com.Redit.clone.Model.VerificationToken;
import com.Redit.clone.Repository.UserRepo;
import com.Redit.clone.Repository.VerificationTokenRepository;
import com.Redit.clone.Service.authService;
import com.Redit.clone.security.JwtProvider;

import io.jsonwebtoken.Jwt;


@Service
public class authServiceImpl implements authService{

	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired 
	MailServiceImpl mailServiceImpl;
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider jwtProvider;
	
	
	
	
	@Override
	@Transactional
	public void signup(UserDto userDto) {
        System.out.println(userDto.getPassword());
        System.out.println(userDto.getEmail());
        System.out.println(userDto.getUserName());

		User user = User.builder().userName(userDto.getUserName())
				.email(userDto.getEmail())
				.password(passwordEncoder.encode("hello"))
				.createdDate(Instant.now())
				.enabled(false).build();
				userRepo.save(user);
				String token=generatVerificationToken( user);
				
				mailServiceImpl.sendMail(new NotificationEmail("Please Activate your account",user.getEmail(), "please click to the below url to activate your account: "+"http://localhost:8080/api/auth/accountVerification/"+token));
				
				
				
	}
	
	private String generatVerificationToken(User user) {
		String token= UUID.randomUUID().toString();
		VerificationToken 	verificationtoken = new VerificationToken();
		verificationtoken.setToken(token);
		verificationtoken.setUser(user);
		verificationTokenRepository.save(verificationtoken);
		return token;
	}
	
	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken   = verificationTokenRepository.findByToken(token);
		if (!verificationToken.isPresent()) {
			throw new SpringRedditException("invalidToken");
		}
			Long userId = verificationToken.get().getUser().getId();
			User user = userRepo.findById(userId).orElseThrow(()->new SpringRedditException("userNotFoundwithTheseId"));
			user.setEnabled(true);
			userRepo.save(user);
			
		
	}

	@Override
	public AuthenticationResponse login(LoginRequest loginRequest) {
   org.springframework.security.core.Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));	
    SecurityContextHolder.getContext().setAuthentication(auth);
    String token=jwtProvider.generateToken(auth);
    return new AuthenticationResponse(token,loginRequest.getUserName());
	}
	
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepo.findByUserName(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }
	
	
	

}
