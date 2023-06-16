package com.shubh.blog.services;

import java.util.List;

import com.shubh.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto cDto);
	CategoryDto updateCategory(CategoryDto cDto, Integer cId);
	void deleteCategory(Integer cId);
	CategoryDto getCategory(Integer cId);
	List<CategoryDto> getCategories();
	
}
