package com.Redit.clone.Model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String  text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="postId" , referencedColumnName = "postId")
	private Post post;
	private Instant createdDate=Instant.now();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId" , referencedColumnName = "id")
	private User user;
}
