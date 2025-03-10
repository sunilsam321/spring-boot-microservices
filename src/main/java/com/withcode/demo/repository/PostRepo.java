package com.withcode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.withcode.demo.entities.Category;
import com.withcode.demo.entities.Post;
import com.withcode.demo.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	
	@Query("select p from Post p where p.title Like :key")
	List<Post> searchByTitle(@Param("key") String title);

}
