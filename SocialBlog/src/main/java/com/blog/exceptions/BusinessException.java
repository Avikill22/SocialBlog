package com.blog.exceptions;



public class BusinessException extends RuntimeException{
	
	private String erroMessage;
	private int errorCode;
	
	public BusinessException(String message, int code){
		this.erroMessage = message;
		this.errorCode = code;
	}

	public String getErroMessage() {
		return erroMessage;
	}

	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
