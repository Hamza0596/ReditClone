package com.Redit.clone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Redit.clone.Model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	public List<Post> findBySuberedditId(Long id);
	public List<Post> findByUserUserName(String name);
	public Post findByImageName(String imagename);
	List<Post> findAllByOrderByCreatedDateDesc();



	
	
	@Query(value ="select p from Post p where lower(p.user.userName) LIKE CONCAT ('%', lower(:un),'%')"+"AND lower(p.posteName) LIKE CONCAT ('%', lower(:pn),'%')")
	public List<Post> findByUserNameAndPosteNameAndVoteCount(String un, String pn );
	

}
