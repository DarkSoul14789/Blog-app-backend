package com.shubh.blog.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubh.blog.entities.Category;
import com.shubh.blog.entities.Post;
import com.shubh.blog.entities.User;
import com.shubh.blog.exceptions.ResourceNotFoundException;
import com.shubh.blog.payloads.PostDto;
import com.shubh.blog.repositories.CategoryRepo;
import com.shubh.blog.repositories.PostRepo;
import com.shubh.blog.repositories.UserRepo;
import com.shubh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto pDto, Integer uId, Integer cId) {
		
		User user = this.userRepo.findById(uId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", uId));
		Category category= this.categoryRepo.findById(cId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Id", cId));
		
		Post post = this.modelMapper.map(pDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public Post updatePost(PostDto pDto, Integer pId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer pId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post getPost(Integer pId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByCategory(Integer cId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByUser(Integer uId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
