package com.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.service.FileService;
import com.blog.service.PostService;
import com.blog.vo.PostVo;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestParam Integer userId) {
		
		return new ResponseEntity<>(postService.createPost(post, userId),HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<PostVo>> retrievePostOfUser(@RequestBody User user){
		return new ResponseEntity<>(postService.getPostByUser(user),HttpStatus.FOUND);
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<PostVo>> getCategories(@RequestBody Category category){
		return new ResponseEntity<List<PostVo>> (postService.getPostByCategory(category),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostVo> getPostById(@PathVariable("id") Integer postId){
		return new ResponseEntity<PostVo> (postService.getPostById(postId),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PostVo> deletePost(@PathVariable("id") Integer postId){
		return new ResponseEntity<PostVo>(postService.deletePost(postId),HttpStatus.OK);
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<PostVo> updatePost(@PathVariable("id") Integer PostId, @RequestBody PostVo postVo){
		return new ResponseEntity<PostVo>(postService.updatePost(postVo, PostId),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PostVo>> getPosts(
				@RequestParam(value = "pageNumber", defaultValue = "0", required = false)Integer pageNumber,
				
				@RequestParam(value = "pageValue", defaultValue = "3", required = false)Integer pageValue){
		return new ResponseEntity<List<PostVo>> (postService.getPosts(pageNumber,pageValue),HttpStatus.OK);
	}
	
	@PostMapping("/image/upload/{id}")
	public ResponseEntity<PostVo> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer id)
		throws IOException{
		
		PostVo postVo = this.postService.getPostById(id);
		
		String fileName = this.fileService.uploadImage(path, image);
		
		
		postVo.setImageName(fileName);
		
		PostVo updatedPost = this.postService.updatePost(postVo,id);
		
		return new ResponseEntity<PostVo>(updatedPost,HttpStatus.OK);
	}
}
