package com.Redit.clone.Dto;

import java.time.Instant;
import java.util.List;

import com.Redit.clone.Model.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuberedditDto {
	private Long id;
	private String name;
	private String description;
	private List <PostDto>Posts ;
	private Instant createdDate;	
	private UserDto user;
}
