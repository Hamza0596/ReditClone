package com.Redit.clone.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.Redit.clone.Service.ServiceImpl.CommentServiceImpl;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Sql(scripts="/User_data.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts="/Post_data.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts="/Comment_data.sql",executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CommentServiceImplTest {
	@Autowired
	CommentServiceImpl commentServiceImpl;
	
	@Test
	void getAllCommentsByPostId() {
		assertEquals("nice",commentServiceImpl.getAllCommentsByPostId(1L).get(0).getText());
	}
	
	@Test
	void getAllCommentsByUserId() {
		assertEquals("Hamza",commentServiceImpl.getAllCommentsByUserId(1L).get(0).getUser().getUserName());
	}
	
	

}
