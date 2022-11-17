package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.payloads.UserDto;


public interface UserService {

	UserDto createUser(UserDto userDto);
	List<UserDto> getAllUsers();
	UserDto delete(Integer UserId);
	UserDto updateUser(UserDto userdto,Integer userId);
	UserDto getUserById(Integer id);
}
