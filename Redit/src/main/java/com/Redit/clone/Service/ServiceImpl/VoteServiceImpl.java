package com.Redit.clone.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Redit.clone.Dto.VoteDto;
import com.Redit.clone.Helpers.ModelMapperConverter;
import com.Redit.clone.Model.Vote;
import com.Redit.clone.Repository.VoteRepository;
import com.Redit.clone.Service.VoteService;

@Service
public class VoteServiceImpl implements VoteService{

	@Autowired
	VoteRepository voteRepo;
	
	@Override
	public void vote(VoteDto voteDto) {
		Vote vote= ModelMapperConverter.map(voteDto, Vote.class);
		voteRepo.save(vote);
	}
	

}
