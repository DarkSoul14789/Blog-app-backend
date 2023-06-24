package com.shubh.blog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.blog.config.AppConstants;
import com.shubh.blog.payloads.ApiResponse;
import com.shubh.blog.payloads.CategoryDto;
import com.shubh.blog.payloads.CategoryResponse;
import com.shubh.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService cService;
	
//	Post category
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cDto) {
		CategoryDto createdCDto = this.cService.createCategory(cDto);
		return new ResponseEntity<CategoryDto>(createdCDto, HttpStatus.CREATED);
	}
	
//	Update category
	@PutMapping("/update/{cId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cDto, @PathVariable Integer cId){
		CategoryDto updatedCDto = this.cService.updateCategory(cDto, cId);
		return new ResponseEntity<CategoryDto>(updatedCDto, HttpStatus.OK);
	}
	
//	Delete category
	@DeleteMapping("/{cId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer cId){
		this.cService.deleteCategory(cId);
		ApiResponse apiResponse = new ApiResponse(true, "Category deleted successfully");
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
//	Get category
	@GetMapping("/{cId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer cId){
		CategoryDto cDto = this.cService.getCategory(cId);
		return new ResponseEntity<CategoryDto>(cDto, HttpStatus.OK);
	}
	
//	Get all categories
	@GetMapping("/")
	public ResponseEntity<CategoryResponse> getCategories(
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber
			){
		CategoryResponse cRes = this.cService.getCategories(pageSize, pageNumber);
		return new ResponseEntity<CategoryResponse>(cRes, HttpStatus.OK);
	}
	
}
