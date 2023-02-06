package com.Redit.clone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Redit.clone.Model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
