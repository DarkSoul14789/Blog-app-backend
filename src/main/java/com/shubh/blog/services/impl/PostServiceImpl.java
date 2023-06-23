package com.shubh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shubh.blog.entities.Category;
import com.shubh.blog.entities.Post;
import com.shubh.blog.entities.User;
import com.shubh.blog.exceptions.ResourceNotFoundException;
import com.shubh.blog.payloads.PostDto;
import com.shubh.blog.payloads.PostResponse;
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
		Post post = this.postRepo.findById(pId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", pId));
		post.setContent(pDto.getContent());
		post.setImageName(pDto.getImageName());
		post.setTitle(pDto.getTitle());
		
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer pId) {
		Post post = this.postRepo.findById(pId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", pId));
		this.postRepo.delete(post);
	}

	@Override
	public PostDto getPost(Integer pId) {
		Post post = this.postRepo.findById(pId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", pId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPosts(Integer pageSize, Integer pageNumber, String sortBy, String sortDir) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("dsc")) {
			sort = Sort.by(sortBy).descending();
		}
		else {
			sort = Sort.by(sortBy).ascending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePosts = this.postRepo.findAll(p);
		List<Post> posts = pagePosts.getContent();
		List<PostDto> postDtos = posts
				.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostsByCategory(Integer cId,Integer pageSize, Integer pageNumber) {
		Category category = this.categoryRepo.findById(cId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Id", cId));
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<Post> pagePosts = this.postRepo.findByCategory(category,p);
		List<Post> posts = pagePosts.getContent();
		List<PostDto> postDtos = posts
				.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		
		return postResponse;
	}

	@Override
	public PostResponse getPostsByUser(Integer uId, Integer pageSize, Integer pageNumber) {
		User user = this.userRepo.findById(uId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", uId));
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePosts = this.postRepo.findByUser(user,p);
		List<Post> posts = pagePosts.getContent();
		List<PostDto> postDtos = posts
				.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
