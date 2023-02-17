package com.Redit.clone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Redit.clone.Model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	public Optional<Vote> findByPostPostIdAndUserIdOrderByVoteIdDesc(Long postId,Long userId);

}
