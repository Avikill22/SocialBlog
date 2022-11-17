package com.blog.exceptions;

public class ApiResponse {
	private String message;
	private int code;
	private boolean successCode;
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiResponse(String message, int code, boolean successCode) {
		super();
		this.message = message;
		this.code = code;
		this.successCode = successCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public boolean isSuccessCode() {
		return successCode;
	}
	public void setSuccessCode(boolean successCode) {
		this.successCode = successCode;
	}
	
	
}
