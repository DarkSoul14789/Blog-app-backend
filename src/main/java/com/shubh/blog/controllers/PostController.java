package com.shubh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
//	Get post by user
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> postDtos = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
//	Get Posts by category
	@GetMapping("/posts/category/{cId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer cId){
		List<PostDto> postDtos = this.postService.getPostsByCategory(cId);
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
//	Get Post by post id
	@GetMapping("/post/{pId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer pId){
		PostDto postDto = this.postService.getPost(pId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
//	Get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> postDtos = this.postService.getPosts();
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
}





