package com.Redit.clone.Repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Redit.clone.Model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List <Comment>findByPostPostIdOrderByCreatedDateDesc(Long id);
	List <Comment> findByUserId(Long id);
	List <Comment> findByUserUserName(String name);

}
