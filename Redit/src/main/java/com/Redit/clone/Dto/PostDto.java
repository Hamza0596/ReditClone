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
	private Integer voteCount=0;
	private int commentCount;
	private String duration;
	public PostDto(String posteName, String url, String description, SuberedditDto subereddit, UserDto user,
			Integer voteCount, int commentCount, String duration) {
		super();
		this.posteName = posteName;
		this.url = url;
		this.description = description;
		this.subereddit = subereddit;
		this.user = user;
		this.voteCount = voteCount;
		this.commentCount = commentCount;
		this.duration = duration;
	}
	
	
}
