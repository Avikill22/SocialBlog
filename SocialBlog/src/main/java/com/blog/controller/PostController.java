package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.service.PostService;
import com.blog.vo.PostVo;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestParam Integer userId) {
		
		return new ResponseEntity<>(postService.createPost(post, userId),HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<PostVo>> retrievePostOfUser(@RequestBody User user){
		return new ResponseEntity<>(postService.getPostByUser(user),HttpStatus.FOUND);
	}

}
