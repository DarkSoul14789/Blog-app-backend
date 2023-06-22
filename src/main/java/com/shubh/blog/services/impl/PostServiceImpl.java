package com.shubh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	public PostDto updatePost(PostDto pDto, Integer pId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer pId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostDto getPost(Integer pId) {
		Post post = this.postRepo.findById(pId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", pId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPosts() {
		List<Post> posts = this.postRepo.findAll();
		List<PostDto> postDtos = posts
				.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer cId) {
		Category category = this.categoryRepo.findById(cId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Id", cId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts
				.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer uId) {
		User user = this.userRepo.findById(uId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", uId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts
				.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
