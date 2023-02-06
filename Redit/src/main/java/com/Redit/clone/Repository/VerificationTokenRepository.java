package com.Redit.clone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Redit.clone.Model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	Optional<VerificationToken> findByToken(String token);
}
