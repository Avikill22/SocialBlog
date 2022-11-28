package com.blog.service.implement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exceptions.BusinessException;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.PostService;

@Service
public class IPostService implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Post createPost(Post post, Integer userId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new BusinessException("Invalid User trying to post blog !!",604));
		
		post.setUser(user);
		post.setPostDate(new Date());
		return postRepository.save(post);
	}

	@Override
	public Post updatePost(Post post, Integer postId) {
		
		Post retrivedPost = postRepository.findById(postId)
				.orElseThrow(()-> new BusinessException("Post with id number : "+postId+" not found.",600));
		
		retrivedPost.setTitle(post.getTitle() != null? post.getTitle():retrivedPost.getTitle());
		retrivedPost.setContent(post.getContent() != null ? post.getContent() : retrivedPost.getContent());
		return postRepository.save(retrivedPost);
	}

	@Override
	public Post deletePost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(()-> new BusinessException("Post with id number : "+postId+" not found.",600));
		
		postRepository.delete(post);
		return post;
	}

	@Override
	public Post getPostById(Integer id) {
		Post post = postRepository.findById(id)
				.orElseThrow(()-> new BusinessException("Post with id number : "+id+" not found.",600));
		
		return post;
	}

	@Override
	public Post getPostByUser(User user) {
		
		return null;
	}

	@Override
	public Post getPostByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

}
