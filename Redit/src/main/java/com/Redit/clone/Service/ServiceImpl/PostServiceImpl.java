package com.Redit.clone.Service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.PostDto;
import com.Redit.clone.Dto.SuberedditDto;
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Exceptions.UserNameNotFoundException;
import com.Redit.clone.Helpers.ModelMapperConverter;
import com.Redit.clone.Model.Post;
import com.Redit.clone.Model.Subereddit;
import com.Redit.clone.Model.User;
import com.Redit.clone.Repository.PostRepository;
import com.Redit.clone.Repository.SubeEditRepository;
import com.Redit.clone.Repository.UserRepo;
import com.Redit.clone.Service.PostService;
import com.Redit.clone.Service.authService;

@Service
public class PostServiceImpl implements PostService{
	@Autowired 
	PostRepository postRepository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    SubeEditRepository subRepo;
    
    
	@Override
	public PostDto createPost(PostDto postDto) {
		System.out.println(postDto);
	User user =userRepo.findByUserName(postDto.getUser().getUserName()).orElseThrow(()->new UserNameNotFoundException("the user of this pos is not found"));
	System.out.println(user);
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
	public List<PostDto> getAllPost() {
		return ModelMapperConverter.mapAll(postRepository.findAllByOrderByCreatedDateDesc(), PostDto.class);
	}

	@Override
	public List<PostDto> getBySubberEditId(Long id) {
		
		return ModelMapperConverter.mapAll(postRepository.findBySuberedditId(id),PostDto.class);
	}

	@Override
	public List<PostDto> getByUserName(String name) {
		// TODO Auto-generated method stub
		return ModelMapperConverter.mapAll(postRepository.findByUserUserName(name),PostDto.class);
	}

}
