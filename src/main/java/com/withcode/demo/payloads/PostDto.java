package com.withcode.demo.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.withcode.demo.entities.Category;
import com.withcode.demo.entities.Comment;
import com.withcode.demo.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostDto {
	
	private String title;
	
	private String content;
	

	private String imageName;
	

	private Date addedDate;
	
	
	private CategoryDto category;
	
	
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();

}
