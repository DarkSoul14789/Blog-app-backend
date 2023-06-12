package com.shubh.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shubh.blog.entities.User;
import com.shubh.blog.payloads.UserDto;
import com.shubh.blog.repositories.UserRepo;
import com.shubh.blog.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUser(Integer userId) {
		User user = this.userRepo.getReferenceById(userId);
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		this.userRepo.deleteById(userId);
		
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
	
}
