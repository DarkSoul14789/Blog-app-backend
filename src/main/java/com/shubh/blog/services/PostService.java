package com.shubh.blog.services;

import java.util.List;

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
	PostResponse getPosts(Integer pageSize, Integer pageNumber);
	
//	get all posts by category
	List<PostDto> getPostsByCategory(Integer cId);
	
//	get all posts by user
	List<PostDto> getPostsByUser(Integer uId);
	
//	search posts by keyword
	List<PostDto> searchPosts(String keyword);
}
