package com.shubh.blog.controllers;

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

import com.shubh.blog.payloads.UserDto;
import com.shubh.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
//	Post user
	@PostMapping(path = "/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}
	
	
//	update user
	@PutMapping(path = "/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId) {
		UserDto updatedUserDto = this.userService.updateUser(userDto, Integer.parseInt(userId));
		return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
	}
	
	
//	delete user
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId) {
		this.userService.deleteUser(Integer.parseInt(userId));
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
//	get user
	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable String userId) {
		UserDto userDto = this.userService.getUser(Integer.parseInt(userId));
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	
//	get all users
	@GetMapping(path = "/")
	public ResponseEntity<List<UserDto>> getUsers() {
		List<UserDto> userDtoList = this.userService.getAllUsers();
		return new ResponseEntity<>(userDtoList, HttpStatus.OK);
	}
}
