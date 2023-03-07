package com.Redit.clone.Service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.Redit.clone.Dto.CommentDto;
import com.Redit.clone.Dto.UserDto;


public interface CommentService {
	public List<CommentDto> getAllCommentsByUserName(String name);
	public CommentDto addComment(CommentDto commentDto)  ;
	public List<CommentDto> getAllCommentsByPostId(Long PostId);
	public List<CommentDto> getAllCommentsByUserId(Long id);

}
