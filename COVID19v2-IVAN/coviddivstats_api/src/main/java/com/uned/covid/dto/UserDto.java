package com.uned.covid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class UserDto {
	private String username;
	private String email;
	private String password;
}