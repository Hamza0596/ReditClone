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
import com.Redit.clone.Dto.SuberedditDto;
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Service.authService;
import com.Redit.clone.Service.ServiceImpl.PostServiceImpl;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Sql(scripts="/User_data.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts="/Post_data.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)



public class PostServicesImplTest {
	
	@Autowired
	PostServiceImpl postServiceImpl;
	
	
	@Test
	void addPostTest() {
		SuberedditDto SuberedditDto = new SuberedditDto();
		UserDto UserDto = new UserDto();
		UserDto.setId(1L);
		SuberedditDto.setId(1L);
		SuberedditDto.setUser(UserDto);
		PostDto post = new PostDto("guitar", "/ddd", "description", SuberedditDto, UserDto, 0, 0, "ccc");
		postServiceImpl.createPost(post);
		assertEquals("guitar",postServiceImpl.getAllPost().get(0).getPosteName());
	}
	@Test
   void getAllPostsTest() {
		
		assertEquals("Annonce",postServiceImpl.getAllPost().get(0).getPosteName());

   }
}
