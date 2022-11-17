package com.blog.exceptions;

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
}
