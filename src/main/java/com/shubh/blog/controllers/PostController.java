package com.shubh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.blog.payloads.PostDto;
import com.shubh.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
//	create
	@PostMapping("/posts/create")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@RequestParam Integer userId,
			@RequestParam Integer categoryId
			)
	{
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
}
