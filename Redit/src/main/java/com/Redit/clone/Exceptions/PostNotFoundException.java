package com.Redit.clone.Exceptions;

public class PostNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String exMessage) {
		super(exMessage);
	}

}
