package com.withcode.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.withcode.demo.entities.Comment;
import com.withcode.demo.entities.Post;
import com.withcode.demo.exceptions.ResourceNotFoundException;
import com.withcode.demo.payloads.CommentDto;
import com.withcode.demo.repository.CommentRepo;
import com.withcode.demo.repository.PostRepo;
import com.withcode.demo.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		
		Comment comment = this.modelMapper.map(commentDto,  Comment.class );
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId", commentId));
		
		this.commentRepo.delete(com);

	}

}
