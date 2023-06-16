package com.shubh.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shubh.blog.entities.Category;
import com.shubh.blog.exceptions.ResourceNotFoundException;
import com.shubh.blog.payloads.CategoryDto;
import com.shubh.blog.repositories.CategoryRepo;
import com.shubh.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo cRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto cDto) {
		Category c = this.dtoToCategory(cDto);
		Category savedC =  cRepo.save(c);
		return this.categoryToDto(savedC);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto cDto, Integer cId) {
		Category c = this.cRepo.findById(cId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Id", cId));
		
		c.setCategoryDescription(cDto.getCategoryDescription());
		c.setCategoryTitle(cDto.getCategoryTitle());
		
		Category updatedC =  this.cRepo.save(c);
		return this.categoryToDto(updatedC);
	}

	@Override
	public void deleteCategory(Integer cId) {
		Category c = this.cRepo.findById(cId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Id", cId));
		this.cRepo.delete(c);		
	}

	@Override
	public CategoryDto getCategory(Integer cId) {
		Category c = this.cRepo.findById(cId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Id", cId));
		
		return this.categoryToDto(c);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> cList = this.cRepo.findAll();
		List<CategoryDto> cDtoList = new ArrayList<CategoryDto>();
		cList.forEach((c)->cDtoList.add(this.categoryToDto(c)));
		return cDtoList;
	}
	
	private CategoryDto categoryToDto(Category c) {
		CategoryDto cDto = this.modelMapper.map(c, CategoryDto.class);
		return cDto;
	}
	
	private Category dtoToCategory(CategoryDto cDto) {
		Category c = this.modelMapper.map(cDto, Category.class);
		return c;
	}
	
}
