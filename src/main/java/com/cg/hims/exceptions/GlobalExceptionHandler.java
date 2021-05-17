package com.cg.hims.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//Exception for quote
	@ExceptionHandler(value=QuoteNotFoundException.class)
	public ResponseEntity<Object> quoteException(QuoteNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Exception for user
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<Object> userException(UserNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Exception for property
	@ExceptionHandler(value=PropertyNotFoundException.class)
	public ResponseEntity<Object> propertyException(PropertyNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Exception for policy holder
	@ExceptionHandler(value=PolicyHolderNotFoundException.class)
	public ResponseEntity<Object> policyHolderException(PolicyHolderNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Exception for policy
	@ExceptionHandler(value=PolicyNotFoundException.class)
	public ResponseEntity<Object> policyException(PolicyNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Exception for agent
	@ExceptionHandler(value=AgentNotFoundException.class)
	public ResponseEntity<Object> agentException(AgentNotFoundException e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
}
