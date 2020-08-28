package com.wleydsonlemos.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wleydsonlemos.workshopmongo.service.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExeceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
	
		StandardError error = StandardError.builder()
											.timestamp(System.currentTimeMillis())
											.status(HttpStatus.NOT_FOUND.value())
											.error("Object not found")
											.message(e.getMessage())
											.path(request.getRequestURI())
											.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
}
