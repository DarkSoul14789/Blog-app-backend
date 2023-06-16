package com.shubh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
