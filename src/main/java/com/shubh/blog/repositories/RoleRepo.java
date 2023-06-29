package com.shubh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubh.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

	
}
