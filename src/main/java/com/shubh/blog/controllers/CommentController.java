package com.shubh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.blog.payloads.ApiResponse;
import com.shubh.blog.payloads.CommentDto;
import com.shubh.blog.services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<CommentDto> createComment(
			@Valid
			@RequestBody CommentDto commentDto,
			@RequestParam Integer userId,
			@RequestParam Integer postId
			){
		CommentDto createdCommentDto = this.commentService.createComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(createdCommentDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		ApiResponse apiResponse = new ApiResponse(true, "Comment successfully deleted");
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
}
