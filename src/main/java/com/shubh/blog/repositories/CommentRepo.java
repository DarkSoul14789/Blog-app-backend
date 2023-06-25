package com.shubh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
