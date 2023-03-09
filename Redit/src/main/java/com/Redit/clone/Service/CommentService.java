package com.Redit.clone.Service;

import java.util.List;


import com.Redit.clone.Dto.CommentDto;


public interface CommentService {
	public List<CommentDto> getAllCommentsByUserName(String name);
	public CommentDto addComment(CommentDto commentDto)  ;
	public List<CommentDto> getAllCommentsByPostId(Long PostId);
	public List<CommentDto> getAllCommentsByUserId(Long id);

}
