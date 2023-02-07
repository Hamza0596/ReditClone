package com.Redit.clone.Service.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.PostDto;
import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Helpers.ModelMapperConverter;
import com.Redit.clone.Model.Post;
import com.Redit.clone.Model.User;
import com.Redit.clone.Repository.PostRepository;
import com.Redit.clone.Repository.UserRepo;
import com.Redit.clone.Service.PostService;
import com.Redit.clone.Service.authService;

@Service
public class PostServiceImpl implements PostService{
	@Autowired 
	PostRepository postRepository;
    @Autowired
    UserRepo userRepo;
    
	@Override
	public PostDto createPost(PostDto postDto) {
	Optional<User> user =userRepo.findById(postDto.getUser().getId());
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
		return ModelMapperConverter.mapAll(postRepository.findAll(), PostDto.class);
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
