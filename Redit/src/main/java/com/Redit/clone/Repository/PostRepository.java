package com.Redit.clone.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Redit.clone.Dto.PostDto;
import com.Redit.clone.Model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	public List<Post> findBySuberedditId(Long id);
	public List<Post> findByUserUserName(String name);
	public Post findByImageName(String imagename);
	Page<Post> findAllByOrderByCreatedDateDesc(Pageable p);



	
	
	@Query(value ="select p from Post p where lower(p.user.userName) LIKE CONCAT ('%', lower(:query),'%')"+"OR lower(p.posteName) LIKE CONCAT ('%', lower(:query),'%')"+"OR lower(p.description) LIKE CONCAT('%', lower(:query),'%') ")
	public Page<Post> findByUserNameAndPosteNameAndDescription(String query, Pageable p );
	

}
