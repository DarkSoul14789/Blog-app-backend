package com.shubh.blog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.blog.config.AppConstants;
import com.shubh.blog.payloads.ApiResponse;
import com.shubh.blog.payloads.UserDto;
import com.shubh.blog.payloads.UserResponse;
import com.shubh.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
//	Post user
	@PostMapping(path = "/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
	}
	
	
//	update user
	@PutMapping(path = "/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updatedUserDto = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);
	}
	
	
//	delete user
//	Admin
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
		this.userService.deleteUser(Integer.parseInt(userId));
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "User deleted successfully"), HttpStatus.OK);
	}
	
//	get user
	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
		UserDto userDto = this.userService.getUser(userId);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	
//	get all users
	@GetMapping(path = "/")
	public ResponseEntity<UserResponse> getUsers(
			@RequestParam(value = "pageSize", defaultValue =AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "pageNumber", defaultValue =AppConstants.PAGE_NUMBER, required = false) Integer pageNumber
			) {
		UserResponse userResponse = this.userService.getAllUsers(pageSize, pageNumber);
		return new ResponseEntity<UserResponse>(userResponse , HttpStatus.OK);
	}
}
