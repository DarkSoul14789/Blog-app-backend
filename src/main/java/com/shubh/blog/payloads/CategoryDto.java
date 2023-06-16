package com.shubh.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	private Integer categoryId;
	
	@NotEmpty(message = "categoryTitle must not be empty")
	@Size(max = 20, message = "Category title should be less than 100 chars")
	private String categoryTitle;
	
	@NotEmpty(message = "Category description should not be empty")
	private String categoryDescription;
}
