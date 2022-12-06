package com.blog.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.PostOfCategory;
import com.blog.entity.User;
import com.blog.exceptions.BusinessException;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostOfCategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.PostService;
import com.blog.vo.PostVo;

@Service
public class IPostService implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostOfCategoryRepository postOfCategoryRepository;
	
	@Override
	public Post createPost(Post post, Integer userId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new BusinessException("Invalid User trying to post blog !!",604));
		
		post.setUser(user);
		post.setPostDate(new Date());
		
		List<PostOfCategory> postOfCategories = post.getPostOfCategory();
		
		
		post.setPostOfCategory(null);
		
		
		postOfCategories
			.stream()
			.filter(postOfCategory -> categoryRepository.findByTitle(postOfCategory.getCategory().getTitle()) == null)
			.forEach(postOfCategory -> {
				
				categoryRepository.save(postOfCategory.getCategory());
			});
		
		
		
		Post savedPost = postRepository.saveAndFlush(post);
		
		postOfCategories
			.stream()
			.forEach(postOfCategory -> {
				postOfCategory.setPost(savedPost);
				postOfCategory.setCategory(categoryRepository.findByTitle(postOfCategory.getCategory().getTitle()));
				postOfCategoryRepository.save(postOfCategory);
			});
		
		return savedPost;
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
	@Transactional
	public PostVo deletePost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(()-> new BusinessException("Post with id number : "+postId+" not found.",600));
		
		PostVo postVo = new PostVo();
		postVo.setTitle(post.getTitle());
		postVo.setContent(post.getContent());
		List<String> categories = new ArrayList<>();
		for(PostOfCategory postOfCategory: post.getPostOfCategory()) {
			Category category = categoryRepository.findById(postOfCategory.getCategory().getCategoryId()).get();
			categories.add(category.getTitle());
		}
		postVo.setCategories(new ArrayList<>(categories));
		
		postOfCategoryRepository.deleteByPost(post);
		postRepository.deleteById(postId);
		
		return postVo;
	}

	@Override
	public PostVo getPostById(Integer id) {
		Post post = postRepository.findById(id)
				.orElseThrow(()-> new BusinessException("Post with id number : "+id+" not found.",600));
		PostVo postVo = new PostVo();
		postVo.setTitle(post.getTitle());
		postVo.setContent(post.getContent());
		List<String> categories = new ArrayList<>();
		for(PostOfCategory postOfCategory: post.getPostOfCategory()) {
			Category category = categoryRepository.findById(postOfCategory.getCategory().getCategoryId()).get();
			categories.add(category.getTitle());
		}
		postVo.setCategories(new ArrayList<>(categories));
		return postVo;
		
	}

	@Override
	public List<PostVo> getPostByUser(User user) {
		List<Post> postOfUser = postRepository.findByUser(user);
		
		
		if(postOfUser == null || postOfUser.size() < 1){
			throw new BusinessException("No Post Found by this user",606); 
		}
		
		List<PostVo> postVoList = new ArrayList<>();
		for(Post post : postOfUser) {
			PostVo postVo = new PostVo();
			postVo.setTitle(post.getTitle());
			postVo.setContent(post.getContent());
			List<String> categories = new ArrayList<>();
			for(PostOfCategory postOfCategory: post.getPostOfCategory()) {
				Category category = categoryRepository.findById(postOfCategory.getCategory().getCategoryId()).get();
				categories.add(category.getTitle());
			}
			postVo.setCategories(new ArrayList<>(categories));
			postVoList.add(postVo);
			
		}
		return postVoList;
	}

	@Override
	public List<PostVo> getPostByCategory(Category category) {
		
		List<PostOfCategory> postOfCategories = postOfCategoryRepository.findByCategory(category);
		if(postOfCategories == null) {
			throw new BusinessException("No Post found with this title "+category.getTitle(),606);
		}
		
		List<PostVo> postVo = new ArrayList<>();
		
		for(PostOfCategory postOfCategory : postOfCategories) {
			PostVo newPostVo = new PostVo();
			Post post = postOfCategory.getPost();
			newPostVo.setTitle(post.getTitle());
			newPostVo.setContent(post.getContent());
			
			List<String> categories = new ArrayList<>();
			for(PostOfCategory postOfCategory1: post.getPostOfCategory()) {
				Category category1 = categoryRepository.findById(postOfCategory1.getCategory().getCategoryId()).get();
				categories.add(category1.getTitle());
			}
			newPostVo.setCategories(new ArrayList<>(categories));
			postVo.add(newPostVo);
		}
		
		return postVo;
	}

}
