package com.Redit.clone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Redit.clone.Model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
