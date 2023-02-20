package com.Redit.clone.Service.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.UserDto;
import com.Redit.clone.Dto.VoteDto;
import com.Redit.clone.Exceptions.PostNotFoundException;
import com.Redit.clone.Exceptions.SpringRedditException;
import com.Redit.clone.Helpers.ModelMapperConverter;
import com.Redit.clone.Model.Post;
import com.Redit.clone.Model.Vote;
import com.Redit.clone.Model.VoteType;
import com.Redit.clone.Repository.PostRepository;
import com.Redit.clone.Repository.VoteRepository;
import com.Redit.clone.Service.VoteService;


@Service
public class VoteServiceImpl implements VoteService{

	@Autowired
	VoteRepository voteRepo;
	@Autowired
	PostRepository postRepo;
	@Autowired
	authServiceImpl auth;
	
	@Override
	public VoteDto vote(VoteDto voteDto) {
		Post post = postRepo.findById(voteDto.getPost().getPostId()).orElseThrow(()->new PostNotFoundException("Post not found with id "+voteDto.getPost().getPostId()));
		Optional<Vote> voteByPostAndUser= voteRepo.findByPostPostIdAndUserIdOrderByVoteIdDesc(post.getPostId(),auth.getCurrentUser().getId());
		if(voteByPostAndUser.isPresent()&&voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
			  throw new SpringRedditException("You have already "
	                    + voteDto.getVoteType() + "'d for this post");
			
		}
		if(VoteType.UPVOTE.equals(voteDto.getVoteType())) {
			post.setVoteCount(post.getVoteCount()+1);
		}
		else {
			post.setVoteCount(post.getVoteCount()-1);

		}
		voteDto.setUser(ModelMapperConverter.map(auth.getCurrentUser(), UserDto.class));
		voteRepo.save(ModelMapperConverter.map(voteDto, Vote.class));
		postRepo.save(post);
		return  voteDto ;
	}
	

}
