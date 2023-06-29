package com.shubh.blog.services;


import com.shubh.blog.payloads.UserDto;
import com.shubh.blog.payloads.UserResponse;

public interface UserService {
	UserDto registerNewUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUser(Integer userId);
	UserResponse getAllUsers(Integer pageSize, Integer pageNumber);
	void deleteUser(Integer userId);
	
}
