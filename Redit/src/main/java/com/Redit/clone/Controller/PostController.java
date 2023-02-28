package com.Redit.clone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Redit.clone.Dto.PostDto;
import com.Redit.clone.Model.Post;
import com.Redit.clone.Repository.PostRepository;
import com.Redit.clone.Service.PostService;


@RequestMapping("api/post")
@RestController
public class PostController {
@Autowired
PostService postService;
@Autowired
PostRepository postRepository;
	
@PostMapping()
public ResponseEntity<Object> addPost(@RequestBody PostDto postDto) {
	PostDto addedPost=postService.createPost(postDto);
	if(addedPost==null) {
		return new ResponseEntity<>("une errur saret ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<>(addedPost,HttpStatus.CREATED);	
}

@GetMapping("/{id}")
public ResponseEntity<Object> getPostById(@PathVariable Long id)   {
	if(postService.getPostById(id)==null) 
		return new ResponseEntity<>("poste Introuvable", HttpStatus.NOT_FOUND);
	
	return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
}


@GetMapping()
public ResponseEntity<Object> getAllPost(){
	if(postService.getAllPost().isEmpty()) {
		return new ResponseEntity<Object>("il ya pas de posts", HttpStatus.ACCEPTED);
	}
	return new ResponseEntity<Object>(postService.getAllPost(), HttpStatus.ACCEPTED);
	
}

@GetMapping("subbereditid/{id}")
public ResponseEntity<Object>getPostBySuuberEdditId(@PathVariable Long id){
	List<PostDto> posts= postService.getBySubberEditId(id);
	if(posts==null) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<>(posts, HttpStatus.NO_CONTENT);
	
	
	
}

@GetMapping("byUserName/{name}")
public List<PostDto> getAllPostByUserName(@PathVariable String name){
	return postService.getByUserName(name);
	
}


@GetMapping("get")
public List<Post> get(){
	return postRepository.findByUserNameAndPosteNameAndVoteCount("TALAN", "guit");
	
}




}
