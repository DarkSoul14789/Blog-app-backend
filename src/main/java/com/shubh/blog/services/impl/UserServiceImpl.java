package com.shubh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shubh.blog.exceptions.*;
import com.shubh.blog.entities.User;
import com.shubh.blog.payloads.UserDto;
import com.shubh.blog.payloads.UserResponse;
import com.shubh.blog.repositories.UserRepo;
import com.shubh.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User"," Id ", userId));
		
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow( ()-> new ResourceNotFoundException("User", "Id", userId) );
		return this.userToDto(user);
	}

	@Override
	public UserResponse getAllUsers(Integer pageSize, Integer pageNumber) {
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<User> usersPage= this.userRepo.findAll(p);
		List<User> users = usersPage.getContent();
		
		List<UserDto> userDtoList = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		UserResponse userResponse = new UserResponse();
		userResponse.setContent(userDtoList);
		userResponse.setPageNumber(usersPage.getNumber());
		userResponse.setPageSize(usersPage.getSize());
		userResponse.setTotalElements(usersPage.getTotalElements());
		userResponse.setTotalPages(usersPage.getTotalPages());
		userResponse.setLastPage(usersPage.isLast());
		return userResponse;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		
		this.userRepo.delete(user);
		
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
	
}
