package com.Redit.clone.Model;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private Integer commentCount;
	private String imageName;
	private String imagePath;
	
	

}
