package com.withcode.demo.services;

import java.util.List;

import com.withcode.demo.entities.Post;
import com.withcode.demo.payloads.PostDto;
import com.withcode.demo.payloads.PostResponse;

public interface PostService {
	
	//create
	
	PostDto createPost(PostDto postDto, Integer userId, Integer CategoryId);
	
	//update 
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	
	void deletePost(Integer postId);
	
	//get all posts;
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get single post
	
	PostDto getPostById(Integer postId);
	
	//get all post by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	
	List<PostDto> getPostsByUser(Integer userId);
	
	//search post
	
	List<PostDto> searchPosts(String Keyword);

}
