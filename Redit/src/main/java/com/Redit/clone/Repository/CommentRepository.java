package com.Redit.clone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Redit.clone.Model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List <Comment> findByPostPostId(Long id);
	List <Comment> findByUserId(Long id);

}
