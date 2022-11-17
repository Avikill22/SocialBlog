package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	
	User findByEmailId(String emailId);

}
