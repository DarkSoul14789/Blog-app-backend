package com.shubh.blog.services;

import com.shubh.blog.payloads.PostDto;
import com.shubh.blog.payloads.PostResponse;

public interface PostService {

//	create
	PostDto createPost(PostDto pDto, Integer uId, Integer cId);
	
//	update
	PostDto updatePost(PostDto pDto, Integer pId);
	
//	delete
	void deletePost(Integer pId);
	
//	get single post
	PostDto getPost(Integer pId);
	
//	get all posts
	PostResponse getPosts(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);
	
//	get all posts by category
	PostResponse getPostsByCategory(Integer cId, Integer pageSize, Integer pageNumber);
	
//	get all posts by user
	PostResponse getPostsByUser(Integer uId, Integer pageSize, Integer pageNumber);
	
//	search posts by keyword
	PostResponse searchPosts(String keyword, Integer pageSize, Integer pageNumber);
}
