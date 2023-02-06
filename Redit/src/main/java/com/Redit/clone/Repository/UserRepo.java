package com.Redit.clone.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Redit.clone.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	public Optional<User> findByUserName(String userName); 

}
