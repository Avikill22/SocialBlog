package com.blog.vo;

public class CommentVo {
	
	Integer commentId;
	
	String Content;
	
	Integer PostId;
	
	Integer UserId;

	public CommentVo() {
		super();
	}

	public CommentVo(Integer commentId, String content, Integer postId, Integer userId) {
		super();
		this.commentId = commentId;
		Content = content;
		PostId = postId;
		UserId = userId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Integer getPostId() {
		return PostId;
	}

	public void setPostId(Integer postId) {
		PostId = postId;
	}

	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}
	
	
}
