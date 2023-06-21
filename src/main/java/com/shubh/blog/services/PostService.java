package com.shubh.blog.services;

import java.util.List;

import com.shubh.blog.entities.Post;
import com.shubh.blog.payloads.PostDto;

public interface PostService {

//	create
	PostDto createPost(PostDto pDto, Integer uId, Integer cId);
	
//	update
	Post updatePost(PostDto pDto, Integer pId);
	
//	delete
	void deletePost(Integer pId);
	
//	get single post
	Post getPost(Integer pId);
	
//	get all posts
	List<Post> getPosts();
	
//	get all posts by category
	List<Post> getPostsByCategory(Integer cId);
	
//	get all posts by user
	List<Post> getPostsByUser(Integer uId);
	
//	search posts by keyword
	List<Post> searchPosts(String keyword);
}
