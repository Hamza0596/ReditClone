package com.Redit.clone.Model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subereddit implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	private List <Post>Posts ;
	private Instant createdDate;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId", referencedColumnName = "id")
    private User user;

}
