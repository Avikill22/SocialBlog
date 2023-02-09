package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.CommentDto;
import com.blog.service.CommentService;
import com.blog.vo.CommentVo;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/{postId}")
	public ResponseEntity<CommentVo> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId, @RequestParam  Integer userId ){
		return new ResponseEntity<CommentVo>(commentService.createComment(comment, postId, userId),HttpStatus.OK);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<HttpStatus> deletedComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.GONE);
	}
	
	
}
