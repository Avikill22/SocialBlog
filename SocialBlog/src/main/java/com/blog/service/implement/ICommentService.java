package com.blog.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exceptions.BusinessException;
import com.blog.payloads.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.service.CommentService;
import com.blog.vo.CommentVo;

@Service
public class ICommentService implements CommentService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public CommentVo createComment(CommentDto commentDto, Integer postId, Integer userId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new BusinessException("Post not found with "+ postId,601));
		
		User user = this.userRepository.findById(userId).orElseThrow(() -> new BusinessException("User not found with "+ userId,601));
		
		Comment comment = new Comment();
		comment.setContent(commentDto.getContent());
		comment.setPost(post);
		comment.setUser(user);
		
		commentRepository.save(comment);
		
		CommentVo commentVo = new CommentVo();
		commentVo.setCommentId(comment.getId());
		commentVo.setContent(comment.getContent());
		commentVo.setPostId(post.getPostId());
		commentVo.setUserId(user.getUserId());
		
		return commentVo;
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		this.commentRepository.deleteById(commentId);
	}

}
