package com.shubh.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be a min of 4 character")
	private String name;
	
	@Email(message = "Email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 10, message = "Password must be between 4 and 10 chars")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@NotEmpty(message = "About must not be empty")
	private String about;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Set<RoleDto> roles = new HashSet<>();
}
