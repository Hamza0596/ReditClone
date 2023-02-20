package com.Redit.clone.Dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	private String authentificationToken;
	private String refreshToken;
	private Instant expiresAt;
	private String userName;
	

}
