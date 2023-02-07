package com.Redit.clone.Service;

import java.util.List;

import com.Redit.clone.Dto.PostDto;

public interface PostService {
	public PostDto createPost(PostDto postDto);
	public PostDto getPostById(Long id) ;
	public List<PostDto> getAllPost();
	public List<PostDto> getBySubberEditId(Long id);
	public List<PostDto> getByUserName(String name);


}
