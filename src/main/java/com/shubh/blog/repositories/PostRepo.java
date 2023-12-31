package com.shubh.blog.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.blog.entities.Category;
import com.shubh.blog.entities.Post;
import com.shubh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	Page<Post> findByUser(User user, Pageable p);
	Page<Post> findByCategory(Category category, Pageable p);
	
	Page<Post> findByTitleContaining(String title, Pageable p);
}
