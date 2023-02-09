package com.blog.payloads;

public class CommentDto {
	String content;
	
	public CommentDto() {
		super();
	}
	
	public CommentDto(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
