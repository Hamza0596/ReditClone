package com.Redit.clone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Redit.clone.Dto.VoteDto;
import com.Redit.clone.Service.VoteService;

@RestController
@RequestMapping("api/votes")
public class VoteController {

	@Autowired
	VoteService voteService;

@PostMapping
public ResponseEntity<Object> vote(@RequestBody VoteDto voteDto){
	voteService.vote(voteDto);
	return new ResponseEntity<>(HttpStatus.OK);
	
}

}
