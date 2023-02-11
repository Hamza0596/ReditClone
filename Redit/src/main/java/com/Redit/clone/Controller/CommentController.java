package com.Redit.clone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Redit.clone.Dto.CommentDto;
import com.Redit.clone.Service.CommentService;
import com.Redit.clone.Service.MailService;

@RestController
@RequestMapping("api/comment")
public class CommentController {
	@Autowired
	CommentService commentService;
	@Autowired
	MailService mailService;
	
	@PostMapping
	public ResponseEntity<Object> addComment(@RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.addComment(commentDto), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getAllCommentForPost(@PathVariable Long id) {
		return new ResponseEntity<Object>(commentService.getAllCommentsByPostId(id), HttpStatus.OK);
		
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<Object> getAllCommentForUser(@PathVariable Long id) {
		return new ResponseEntity<Object>(commentService.getAllCommentsByUserId(id), HttpStatus.OK);
		
	}
	
	
	


}
