package com.covid19.common.exception;

import com.covid19.common.messages.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value=HttpStatus.NOT_FOUND)  //404
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -2217466109027636423L;

	public ResourceNotFoundException() {
		super(ErrorMessage.RESOURCE_NOT_FOUND);
	}
	
	public ResourceNotFoundException(String message){
		super(String.format(ErrorMessage.RESOURCE_NOT_FOUND, message));
	}
}
