package com.Redit.clone.Dto;

import com.Redit.clone.Model.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
	private VoteType voteType;
	private PostDto post;
	private UserDto user;

}
