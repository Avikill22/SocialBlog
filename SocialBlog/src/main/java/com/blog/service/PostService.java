package com.blog.service;

import java.util.List;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;

public interface PostService {

	public Post createPost(Post post, Integer user);
	
	public Post updatePost(Post post,Integer postId);
	
	public Post deletePost(Integer postId);
	
	public Post getPostById(Integer id);
	
	public Post getPostByUser(User user);
	
	public Post getPostByCategory(Category category);
}
