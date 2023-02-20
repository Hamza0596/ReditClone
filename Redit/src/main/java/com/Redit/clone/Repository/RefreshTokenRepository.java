package com.Redit.clone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Redit.clone.Model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

 Optional<RefreshToken> findByToken(String refreshToken);

  void deleteByToken(String refreshToken);

}
