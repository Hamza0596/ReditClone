package com.Redit.clone.Model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
	
	@javax.persistence.Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	private String posteName;
	@Nullable
	private String url;
	@Nullable
	private String description;
	private Integer voteCount;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId", referencedColumnName = "id")
	private User user;
	private Instant createdDate= Instant.now();
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subId", referencedColumnName = "id")
	private Subereddit subereddit;
	
	

}
