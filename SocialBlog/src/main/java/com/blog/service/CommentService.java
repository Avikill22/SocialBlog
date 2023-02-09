package com.blog.service;

import com.blog.entity.Comment;
import com.blog.payloads.CommentDto;
import com.blog.vo.CommentVo;

public interface CommentService {
	
	void deleteComment(Integer commentId);

	CommentVo createComment(CommentDto commentDto, Integer postId, Integer userId);
}
