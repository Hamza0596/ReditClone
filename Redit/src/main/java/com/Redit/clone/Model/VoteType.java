package com.Redit.clone.Model;

public enum VoteType {
UPVOTE(1),DownVote(-1);
	
	final int direction ; 
	VoteType(int direction){
		this.direction=direction;
	}
	
}
