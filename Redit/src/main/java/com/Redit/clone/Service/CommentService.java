package com.Redit.clone.Service;

import java.util.List;

import com.Redit.clone.Dto.CommentDto;


public interface CommentService {
	public CommentDto addComment(CommentDto commentDto)  ;
	public List<CommentDto> getAllCommentsByPost(Long PostId);

}
