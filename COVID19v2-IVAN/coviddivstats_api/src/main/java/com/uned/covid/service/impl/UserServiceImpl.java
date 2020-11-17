package com.uned.covid.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uned.covid.dto.UserDto;
import com.uned.covid.entity.User;
import com.uned.covid.exception.ControlException;
import com.uned.covid.exception.NotFoundException;
import com.uned.covid.json.UserRest;
import com.uned.covid.repository.UserRepository;
import com.uned.covid.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	public static final ModelMapper modelMapper = new ModelMapper();

	public UserRest readUser(Long userId) throws ControlException {

		return modelMapper.map(getUserEntity(userId), UserRest.class);
	}

	public String deleteUser(String username) throws ControlException {
		User user = getUserEntity(username);
		if(user.getLevel() != 9) {
			userRepository.delete(user);
			return "detele!";
		}else {
			return null;
		}
	}	
	
	public String updateUser(Long userId) throws ControlException {
		User user = getUserEntity(userId);
		System.out.println(user.getUsername());
		// actualizar aqui el usuario
		return "update!";
	}
	
	public UserRest readUserByName(String name) throws ControlException {
		User user = getUserEntity(name);
		
		if(user == null) {
			throw new NotFoundException("SNOT-404-2", "USER_NOT_FOUND");
		}
		return modelMapper.map(user, UserRest.class);
	}
	
	
	public List<UserRest> readUsers() throws ControlException {
		final List<User> covidEntities = userRepository.findAll();
		return covidEntities.stream().map(service -> modelMapper.map(service, UserRest.class))
				.collect(Collectors.toList());
	}
	
	public UserRest createUser(UserDto newUser) throws ControlException{
		User user = userRepository.
				save(new User(
						newUser.getUsername(),
						newUser.getEmail(),
						newUser.getPassword(),
						createHash(10)));
		if(user == null) {
			throw new NotFoundException("SNOT-403-1", "USER_NOT_CREATED");
		}
		return modelMapper.map(user, UserRest.class);
	}
	
	private User getUserEntity(Long userId) throws ControlException {

		return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("SNOT-404-2", "USER_NOT_FOUND"));
	}

	private User getUserEntity(String username) {

		User user =  userRepository.findByUsername(username);
	
		return user;		
	}
	
	/**
	 * Sirve para crear un hash aleatorio, pasando la longitud.
	 * @param count La longitud.
	 * @return El hash.
	 */
	private String createHash(int count)
	{
		char[] chars = "ABCDEFGHJKMNPQRSTUVWXYZ123456789".toCharArray();
		StringBuilder sb = new StringBuilder(count);
		Random random = new Random();
		
		for (int i = 0; i < count; i++) 
		{
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}