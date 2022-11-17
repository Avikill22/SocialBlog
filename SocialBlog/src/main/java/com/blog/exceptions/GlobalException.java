package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.hibernate.loader.PropertyPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse> businessLogicException(BusinessException ex){
		String message = ex.getErroMessage();
		int code =  ex.getErrorCode();
		ApiResponse apiResponse = new ApiResponse(message,code,false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String,String>> handleTransactionSystemException(ConstraintViolationException ex){
		Map<String,String> result = new HashMap<>();
		ex.getConstraintViolations().forEach((error)->
		result.put(error.getPropertyPath().toString(), error.getMessage().toString()));
		
		return new ResponseEntity<Map<String,String>>(result,HttpStatus.BAD_REQUEST);
	}
}
