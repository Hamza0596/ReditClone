package com.Redit.clone.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.Redit.clone.Dto.PostDto;

public interface PostService {
	public PostDto createPost(PostDto postDto, MultipartFile file) throws IllegalStateException, IOException;
	public PostDto getPostById(Long id) ;
	public Page<PostDto> getAllPost(int page);
	public List<PostDto> getBySubberEditId(Long id);
	public List<PostDto> getByUserName(String name);
	public byte[] downloadImage(String fileName) throws IOException;
	public Page<PostDto> searchPosts(int page, String query);
	public void deletePostById(Long id);


}
