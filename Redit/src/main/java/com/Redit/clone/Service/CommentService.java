package com.Redit.clone.Service;

import java.util.List;


import com.Redit.clone.Dto.CommentDto;
import com.Redit.clone.Dto.UserDto;


public interface CommentService {
	public CommentDto addComment(CommentDto commentDto)  ;
	public List<CommentDto> getAllCommentsByPostId(Long PostId);
	public List<CommentDto> getAllCommentsByUserId(Long id);

}
