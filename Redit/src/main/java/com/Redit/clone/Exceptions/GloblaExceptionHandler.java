package com.Redit.clone.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GloblaExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public ResponseEntity<?> handelResourceNotFoundException(UserNameNotFoundException exception  ){
		ErrorDetails  errorDetails = new ErrorDetails( new Date(), exception.getMessage(),exception.getMessage());
		return  new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<?> handelResourceNotFoundException(PostNotFoundException exception  ){
		ErrorDetails  errorDetails = new ErrorDetails( new Date(), exception.getMessage(),exception.getMessage());
		return  new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
	}
}
