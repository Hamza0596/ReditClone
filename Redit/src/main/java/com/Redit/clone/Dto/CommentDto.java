package com.Redit.clone.Dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private Long id;
	private Long postId;
	private Instant createdDate=Instant.now();
	private String text;
	private String userName;
	

}
