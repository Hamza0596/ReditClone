package com.Redit.clone.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	private Long postId;
	private String posteName;
	private String url;
	private String description;
	private SuberedditDto subereddit;
	private UserDto user;
}
