package com.Redit.clone.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Redit.clone.Dto.PostDto;
import com.Redit.clone.Dto.SuberedditDto;
import com.Redit.clone.Dto.UserDto;
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
public ResponseEntity<Object> addPost(@RequestParam String posteName , @RequestParam String url,@RequestParam String description, @RequestParam Long suberedditId, @RequestParam String userName, @RequestParam(required=false) MultipartFile file) throws IllegalStateException, IOException {
	SuberedditDto sub = new SuberedditDto();
	UserDto user = new UserDto();
	user.setUserName(userName); 
	PostDto postDto = new PostDto();
	postDto.setPosteName(posteName);
	postDto.setUrl(url);
	postDto.setDescription(description);
	sub.setId(suberedditId);
	postDto.setSubereddit(sub);
	postDto.setUser(user);
	
	PostDto addedPost=postService.createPost(postDto,file);
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


@GetMapping("all/{page}")
public ResponseEntity<Object> getAllPost(@PathVariable int page){
	if(postService.getAllPost(page).isEmpty()) {
		return new ResponseEntity<Object>("il ya pas de posts", HttpStatus.ACCEPTED);
	}
	return new ResponseEntity<Object>(postService.getAllPost(page), HttpStatus.ACCEPTED);
	
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


@GetMapping("search/{query}/{pageNumber}")
public Page<PostDto> searchFilter(@PathVariable String query  , @PathVariable int pageNumber ){
	return postService.searchPosts(pageNumber,query);
	
}

@GetMapping("image/{fileName}")
public ResponseEntity<Object> downloadImage (@PathVariable String fileName) throws IOException{
byte[] imageData = postService.downloadImage(fileName);
return ResponseEntity.status(HttpStatus.OK)
.contentType(MediaType.valueOf("image/png"))
.body(imageData) ;
}

@DeleteMapping("/{id}")
public void deletePostById(@PathVariable Long id) {
	
	postService.deletePostById(id);
}





}
