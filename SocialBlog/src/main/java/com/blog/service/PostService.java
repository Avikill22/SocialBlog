package com.blog.service;

import java.util.List;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.vo.PostVo;

public interface PostService {

	public Post createPost(Post post, Integer user);
	
	public Post updatePost(Post post,Integer postId);
	
	public Post deletePost(Integer postId);
	
	public PostVo getPostById(Integer id);
	
	public List<PostVo> getPostByUser(User user);
	
	public List<PostVo> getPostByCategory(Category category);
}
