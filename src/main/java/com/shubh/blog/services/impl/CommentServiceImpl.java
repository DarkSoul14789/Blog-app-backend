package com.shubh.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shubh.blog.entities.Comment;
import com.shubh.blog.entities.Post;
import com.shubh.blog.entities.User;
import com.shubh.blog.exceptions.ResourceNotFoundException;
import com.shubh.blog.payloads.CommentDto;
import com.shubh.blog.repositories.CommentRepo;
import com.shubh.blog.repositories.PostRepo;
import com.shubh.blog.repositories.UserRepo;
import com.shubh.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		comment.setUser(user);
		
		Comment createdComment = this.commentRepo.save(comment);
		return this.modelMapper.map(createdComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
		this.commentRepo.delete(comment);
		
	}
}
