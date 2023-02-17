package com.Redit.clone.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.Redit.clone.Dto.LoginRequest;
import com.Redit.clone.Dto.PostDto;
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Dto.VoteDto;
import com.Redit.clone.Model.VoteType;
import com.Redit.clone.Service.authService;
import com.Redit.clone.Service.ServiceImpl.VoteServiceImpl;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Sql(scripts="/User_data.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts="/Post_data.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class VoteServiceImplTest {
	
	@Autowired
	VoteServiceImpl voteServiceImpl;
	
	@Test 
	void addVoteTest() {
		
		
		
		
		
		PostDto post= new PostDto();
		post.setPostId(1L);
		
		UserDto user = new UserDto();
		user.setId(1L);
		user.setUserName("Hamza");
		user.setPassword("hello");;

		
		
		VoteDto vote= new VoteDto();
		vote.setPost(post);
		vote.setUser(user);
		vote.setVoteType(VoteType.UPVOTE);
		
		assertEquals("Hamza",voteServiceImpl.vote(vote).getUser().getUserName());

	}

}
