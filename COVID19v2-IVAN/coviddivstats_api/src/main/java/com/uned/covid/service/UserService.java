package com.uned.covid.service;

import java.util.List;

import com.uned.covid.dto.UserDto;
import com.uned.covid.exception.ControlException;
import com.uned.covid.json.UserRest;

public interface UserService {
		
	
	// CRUD
	UserRest createUser(UserDto user) throws ControlException;	
	UserRest readUser(Long Long) throws ControlException;	
	String	 updateUser(Long Long) throws ControlException;	
	String   deleteUser(String username) throws ControlException;
			
	UserRest readUserByName(String name) throws ControlException;	
	List<UserRest> readUsers() throws ControlException;	
}