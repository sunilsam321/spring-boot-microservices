package com.withcode.demo.payloads;

import javax.validation.constraints.Email;
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
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message ="Users name must be min of 4 charaters !!" )
	private String name;
	
	@Email(message ="Email address is not Valid !!")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")
	
	private String password;
	
	@NotEmpty
	private String about;

}
