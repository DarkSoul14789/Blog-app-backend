package com.shubh.blog.payloads;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	
	@NotEmpty(message = "Post Title must not be empty")
	@Size(max = 50, message = "Post title should be less than 50 chars")
	private String title;
	
	@NotEmpty(message = "Post Content must not be empty")
	@Size(max = 2000, message = "Category title should be less than 2000 chars")
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private List<CommentDto> comments = new ArrayList<CommentDto>();
}
