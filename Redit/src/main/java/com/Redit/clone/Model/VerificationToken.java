package com.Redit.clone.Model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
	@javax.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String token;
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	private Instant expiryDate;
	

	
}
