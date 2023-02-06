package com.Redit.clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Redit.clone.Service.ServiceImpl.UserDetailsServiceImpl;

@SpringBootApplication
@EnableAsync
public class ReditApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReditApplication.class, args);
	}
	@Bean
	PasswordEncoder PasswordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	
	
	
	
	



}
