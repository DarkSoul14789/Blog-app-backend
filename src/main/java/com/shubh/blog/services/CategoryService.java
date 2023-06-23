package com.shubh.blog.services;


import com.shubh.blog.payloads.CategoryDto;
import com.shubh.blog.payloads.CategoryResponse;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto cDto);
	CategoryDto updateCategory(CategoryDto cDto, Integer cId);
	void deleteCategory(Integer cId);
	CategoryDto getCategory(Integer cId);
	CategoryResponse getCategories(Integer pageSize, Integer pageNumber);
	
}
