package com.Redit.clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.Redit.clone.Config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@CrossOrigin
public class ReditApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReditApplication.class, args);
	}
	@Bean
	PasswordEncoder PasswordEncoder() {
		return  new BCryptPasswordEncoder();
	}
	
	
	
	
	



}
