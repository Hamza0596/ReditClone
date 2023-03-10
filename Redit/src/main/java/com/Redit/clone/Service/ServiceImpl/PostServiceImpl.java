package com.Redit.clone.Service.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Redit.clone.Dto.PostDto;
import com.Redit.clone.Dto.SuberedditDto;
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Exceptions.UserNameNotFoundException;
import com.Redit.clone.Helpers.ModelMapperConverter;
import com.Redit.clone.Model.Post;
import com.Redit.clone.Model.Subereddit;
import com.Redit.clone.Model.User;
import com.Redit.clone.Repository.CommentRepository;
import com.Redit.clone.Repository.PostRepository;
import com.Redit.clone.Repository.SubeEditRepository;
import com.Redit.clone.Repository.UserRepo;
import com.Redit.clone.Service.PostService;


@Service
public class PostServiceImpl implements PostService{
	
	private String folderPath="C:\\Users\\hbouachir\\Pictures\\Saved Pictures\\";
	@Autowired 
	PostRepository postRepository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    SubeEditRepository subRepo;
    @Autowired
    CommentRepository commentRepository;
    
    
	@Override
	public PostDto createPost(PostDto postDto , MultipartFile file) throws IllegalStateException, IOException {
		 
     if(file!=null) {
    		String filePath = this.folderPath + file.getOriginalFilename();	
    		file.transferTo(new File(filePath));
    		postDto.setImagePath(filePath); 
    		postDto.setImageName(file.getOriginalFilename());

     }


		
	User user =userRepo.findByUserName(postDto.getUser().getUserName()).orElseThrow(()->new UserNameNotFoundException("the user of this pos is not found"));
	Subereddit sub = subRepo.findById(postDto.getSubereddit().getId()).orElseThrow(()->new UserNameNotFoundException("waw"));
	postDto.setSubereddit(ModelMapperConverter.map(sub, SuberedditDto.class));
	UserDto userDto=ModelMapperConverter.map(user,UserDto.class);
	postDto.setUser(userDto);
    Post newPost =ModelMapperConverter.map(postDto, Post.class);
    PostDto addedPost= ModelMapperConverter.map(postRepository.save(newPost),PostDto.class);
    return addedPost;
	}
	
	


	@Override
	public PostDto getPostById(Long id) {
		return ModelMapperConverter.map(postRepository.findById(id), PostDto.class);
	}

	
	@Override
	public Page<PostDto> getAllPost(int page) {
		Page<Post> posts= postRepository.findAllByOrderByCreatedDateDesc(PageRequest.of(page, 7, Sort.by("createdDate").descending()));
		posts.stream().forEach(x->x.setCommentCount(commentRepository.countByPostPostId(x.getPostId())));
		
		return posts.map(entity -> ModelMapperConverter.map(entity, PostDto.class)); 
	}

	@Override
	public List<PostDto> getBySubberEditId(Long id) {
		
		return ModelMapperConverter.mapAll(postRepository.findBySuberedditId(id),PostDto.class);
	}

	@Override
	public List<PostDto> getByUserName(String name) {
		return ModelMapperConverter.mapAll(postRepository.findByUserUserName(name),PostDto.class);
	}

	@Override
	public byte[] downloadImage(String fileName) throws IOException {
		 Optional<Post> post =Optional.of(postRepository.findByImageName(fileName));
		 String filePath = post.get().getImagePath();
		 byte[] image = Files.readAllBytes(new File(filePath).toPath());
		return image;
	}

}
