package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.UserDto;


public interface UserService {

	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto);
	UserDto getUserById(UserDto userDto);
	List<UserDto> getAllUsers();
	void delete(Integer UserId);
}
