package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
