package com.Redit.clone;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.Redit.clone.Config.SwaggerConfiguration;

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
	
	   @Bean
	    public CorsFilter corsFilter() {
	    	final String ACAO = "Access-Control-Allow-Origin";
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.setAllowCredentials(true);
	        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", ACAO, "Content-Type",
	                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
	                "Access-Control- Request-Method", "Access-Control-Request-Headers"));
	        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
	                ACAO, ACAO, "Access-Control-Allow-Credentials"));
	        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	        return new CorsFilter(urlBasedCorsConfigurationSource);
	    }

	
	
	
	
	



}
