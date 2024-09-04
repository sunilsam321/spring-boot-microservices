package com.withcode.demo.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor


public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4, message = "Category Title characters are must be upto 4 charaters")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 4, message = "Category Description characters are must be upto 4 charaters")
	private String categoryDescription;

}
