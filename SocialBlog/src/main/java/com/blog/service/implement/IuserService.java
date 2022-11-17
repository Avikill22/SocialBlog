package com.blog.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.exceptions.BusinessException;
import com.blog.payloads.UserDto;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;

@Service
public class IuserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		if(userDto == null) {
			throw new BusinessException("No input is present.",601);
		}
		
		User user = new User();
		
		user.setName(userDto.getName());
		user.setEmailId(userDto.getEmailId());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		userRepository.save(user);
		
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userdto,Integer userId) {
		
		if(userdto == null) {
			throw new BusinessException("No input is present.",601);
		}
		
		Optional<User> userInfo = userRepository.findById(userId);
		
		if(userInfo.isPresent()) {
			userInfo.ifPresent(u -> u.setName(userdto.getName() == null? u.getName():userdto.getName()));
			userInfo.ifPresent(u -> u.setEmailId(userdto.getEmailId() == null? u.getEmailId():userdto.getEmailId()));
			userInfo.ifPresent(u -> u.setAbout(userdto.getAbout() == null? u.getAbout():userdto.getAbout()));
			userInfo.ifPresent(u -> u.setPassword(userdto.getPassword() == null? u.getPassword():userdto.getPassword()));
			User updatedUser = userRepository.save(userInfo.get());
			return convertUserToUderDto(updatedUser);
		}else {
			throw new BusinessException("No user information present with id : " + userId,600);
		}
		
	}

	@Override
	public UserDto getUserById(Integer userId) {
		Optional<User> userInfo = userRepository.findById(userId);
		
		if(userInfo.isPresent()) {
			return convertUserToUderDto(userInfo.get());
		}else {
			throw new BusinessException("No user information present with id : " + userId,600);
		}
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> userInformations = userRepository.findAll();
		
		List<UserDto> userDtoInformations = userInformations.stream().map(u -> convertUserToUderDto(u)).collect(Collectors.toList());
		return userDtoInformations;
	}

	@Override
	public UserDto delete(Integer userId) {
		
		Optional<User> userPresent = userRepository.findById(userId);
		
		if(userPresent.isPresent()) {
			userRepository.delete(userPresent.get());
			return convertUserToUderDto(userPresent.get()); 
		}else {
			throw new BusinessException("No user information present with id : " + userId,600);
		}
	}
	
	UserDto convertUserToUderDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmailId(user.getEmailId());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
