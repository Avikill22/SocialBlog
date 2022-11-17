package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.User;
import com.blog.payloads.UserDto;
import com.blog.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping()
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		return new ResponseEntity<UserDto>(userService.createUser(userDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUSerById(@PathVariable("userId") Integer userId){
		return new ResponseEntity<UserDto>(userService.getUserById(userId),HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserDto>> getUSerById(){
		return new ResponseEntity<List<UserDto>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@PutMapping("/{userid}")
	public ResponseEntity<UserDto> updateUserInformation(@RequestBody UserDto userdto,@PathVariable Integer userid){
		return new ResponseEntity<UserDto>(userService.updateUser(userdto,userid),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserDto> deleteUserInformation(@PathVariable Integer userId){
		return new ResponseEntity<UserDto>(userService.delete(userId),HttpStatus.ACCEPTED);
	}
}
