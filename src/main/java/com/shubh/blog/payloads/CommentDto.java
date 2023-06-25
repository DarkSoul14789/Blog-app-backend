package com.shubh.blog.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

	private int id;
	
	@NotEmpty(message = "Content should not be empty")
	@Size(max = 500, message = "Comment should be less than 500 characters")
	private String content;
	
	private UserDto user;
}
