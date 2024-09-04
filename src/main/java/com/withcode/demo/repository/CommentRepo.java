package com.withcode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.withcode.demo.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment , Integer> {
	
	

}
