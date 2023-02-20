package com.Redit.clone.Service.ServiceImpl;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Exceptions.SpringRedditException;
import com.Redit.clone.Model.RefreshToken;
import com.Redit.clone.Repository.RefreshTokenRepository;
import com.Redit.clone.Service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	RefreshTokenRepository refreshTokenRepo;
	
	@Override
	public RefreshToken generateRefreshToken() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreatedDate(Instant.now());
		
		return refreshTokenRepo.save(refreshToken);
    
		
	}
	@Override
	public void validateRefreshToken(String token) {
	refreshTokenRepo.findByToken(token).orElseThrow(()-> new SpringRedditException("invalid refresh token"));	
	}
	
	@Override
	@Transactional
	public void deleteRefreshToken(String refreshToken ) {
		refreshTokenRepo.deleteByToken(refreshToken);
	}

}
