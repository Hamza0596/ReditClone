package com.Redit.clone.Dto;

import java.time.Instant;

import com.Redit.clone.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private Long id;
	private PostDto post;
	private Instant createdDate=Instant.now();
	private String text;
	private UserDto user;
	

}
