package com.shubh.blog.services;

import java.util.List;

import com.shubh.blog.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUser(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	
}
