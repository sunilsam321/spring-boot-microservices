package com.withcode.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.withcode.demo.config.AppConstants;
import com.withcode.demo.entities.Post;
import com.withcode.demo.payloads.ApiResponse;
import com.withcode.demo.payloads.PostDto;
import com.withcode.demo.payloads.PostResponse;
import com.withcode.demo.services.FileService;
import com.withcode.demo.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	  public ResponseEntity<PostDto> createPost(
		  @RequestBody PostDto postDto,
		  @PathVariable Integer userId,
		  @PathVariable Integer categoryId
		  
		  )
	  {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	  
	  }
	
	//get by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		
	}
	
	//get by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		
	}
	
	//get all posts
	  @GetMapping("/posts")
	  public ResponseEntity<PostResponse> getAllPost(
			  
			  //Pagination Methods
			  
			  @RequestParam(value = "pageNumber",defaultValue =AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			  @RequestParam(value ="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			  
	  // Sorting Methods
			  
	  		  @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
	  		  @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_DIR,required = false) String sortDir)
	  {
		  //List<PostDto> allPost = this.postService.getAllPost(pageNumber, pageSize);
		  PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		  
		 // return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
		  return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	  }
	
	
	//get post details by id

	  @GetMapping("/posts/{postId}")
	  public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	  {
		  PostDto postDto = this.postService.getPostById(postId);
		  
		  return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	  }
	  
	  //delete post
	  @DeleteMapping("/posts/{postId}")
	  public ApiResponse deletePost(@PathVariable Integer postId) {
		  
		 this.postService.deletePost(postId);
		 
		 return new ApiResponse("Post is deleted Successfully", true);
	  }
	  
	  
	  //update post
	  @PutMapping("/posts/{postId}")
	  public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, Integer postId) {
		  
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		 
		 return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	  }
	  
	  //search
	  
	  @GetMapping("/posts/search/{keywords}")
	  public ResponseEntity<List<PostDto>> searchPostBytitle(@PathVariable("keywords") String keywords ) {
		   
		   List<PostDto> result = this.postService.searchPosts(keywords);
		   
		   return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	  }
	  
	  //Post Image Upload
	  
	  @PostMapping("/post/image/uploads/{postId}")
	  public ResponseEntity<PostDto> uploadPostImage(
			  @RequestParam("image") MultipartFile image,
			  @PathVariable Integer postId ) throws IOException
			  {
		  
		  
		  PostDto postDto =this.postService.getPostById(postId);
		  
		  String fileName = this.fileService.uploadImage(path, image);
		 
		  postDto.setImageName(fileName);
		  PostDto updatePost=this.postService.updatePost(postDto, postId);
		  
		  return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
		  
	  }
	  
	  @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_PNG_VALUE)
	  public void downloadImage(
			  @PathVariable("imageName") String imageName,
			  HttpServletResponse response
			  ) throws IOException {
		  
		  InputStream resource = this.fileService.getResource(path, imageName);
		  response.setContentType(MediaType.IMAGE_PNG_VALUE);
		  StreamUtils.copy(resource,response.getOutputStream());
	  }
	  
	  
	
}
