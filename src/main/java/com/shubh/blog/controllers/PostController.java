package com.shubh.blog.controllers;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.blog.payloads.ApiResponse;
import com.shubh.blog.payloads.PostDto;
import com.shubh.blog.payloads.PostResponse;
import com.shubh.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
//	create
	@PostMapping("/posts/create")
	public ResponseEntity<PostDto> createPost(
			@Valid
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
	public ResponseEntity<PostResponse> getPostsByUser(
			@PathVariable Integer userId,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber
			){
		PostResponse postResponse = this.postService.getPostsByUser(userId, pageSize, pageNumber);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
//	Get Posts by category
	@GetMapping("/posts/category/{cId}")
	public ResponseEntity<PostResponse> getPostsByCategory(
			@PathVariable Integer cId,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber
			){
		PostResponse postResponse = this.postService.getPostsByCategory(cId,pageSize,pageNumber);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
//	Get Post by post id
	@GetMapping("/post/{pId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer pId){
		PostDto postDto = this.postService.getPost(pId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
//	Get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
			){
		PostResponse postDtos = this.postService.getPosts(pageSize, pageNumber, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postDtos, HttpStatus.OK);
	}
	
//	Delete post
	@DeleteMapping("/post/delete/{pId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer pId){
		this.postService.deletePost(pId);
		ApiResponse apiResponse = new ApiResponse(true, "Post successfully deleted");
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
//	Update post
	@PutMapping("/post/update/{pId}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto pDto, @PathVariable Integer pId){
		PostDto updatedPostDto = this.postService.updatePost(pDto, pId);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}
	
//	Search post
	@GetMapping("/posts/searchBy/{keyword}")
	public ResponseEntity<PostResponse> searchPostByTitle(
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@PathVariable String keyword
			){
		PostResponse postResponse = this.postService.searchPosts(keyword, pageSize, pageNumber);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
}





