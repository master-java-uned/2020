package com.uned.covid.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uned.covid.dto.UserDto;
import com.uned.covid.exception.ControlException;
import com.uned.covid.json.UserRest;
import com.uned.covid.response.Response;
import com.uned.covid.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/" + "v${app.version}")
public class UserController {


	@Autowired
	UserService userService;
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<UserRest> login(@RequestParam(required=true,name="user") String user, @RequestParam(required=true,name="password") String password) throws ControlException {
		
		System.out.println("user:" + user + " password:"+ password);
		
		try {
			// si el user es null lanzo excepcion
			UserRest userRest = userService.readUserByName(user);
				
			System.out.println(user + " " + password);
			String token = getJWTToken(user);

			userRest.setUsername(user);
			userRest.setToken(token);		

			return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),HttpStatus.OK.getReasonPhrase(), userRest);

		} catch (ControlException e) {
			
			return new Response<>(HttpStatus.NOT_FOUND.value(), e.getCode(),e.getMessage(), null);
		}
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/user/{" + "username"+ "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<UserRest> getUser(@PathVariable String username) throws ControlException {
			
		try {
			// si el user es null lanzo excepcion
			UserRest userRest = userService.readUserByName(username);

			return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),HttpStatus.OK.getReasonPhrase(), userRest);

		} catch (ControlException e) {
			
			return new Response<>(HttpStatus.NOT_FOUND.value(), e.getCode(),e.getMessage(), null);
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/user/{" + "username"+ "}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<String> removeUser(@PathVariable String username) throws ControlException {
			
		String resp = userService.deleteUser(username);		
		if(resp == null) {			
			return new Response<>(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),HttpStatus.NOT_FOUND.toString(), null);
		}else {
			return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),HttpStatus.OK.getReasonPhrase(), resp);
		}		
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@RequestMapping(value = "/users/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<UserRest>> getUsers() throws ControlException {
		
		try {
			// si el user es null lanzo excepcion
			List<UserRest> usersRest = userService.readUsers();
			

			return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),HttpStatus.OK.getReasonPhrase(), usersRest);

		} catch (ControlException e) {
			
			return new Response<>(HttpStatus.NOT_FOUND.value(), e.getCode(),e.getMessage(), null);
		}
	}
	
	
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<UserRest> register(
			@RequestParam(required=true,name="user") String user,
			@RequestParam(required=true,name="mail") String mail, 
			@RequestParam(required=true,name="password") String password) throws ControlException {
		
		System.out.println("user to rgister{ name:" + user + " mail:" + mail + " pass:" + password);
		
		try {

			UserDto userDto = new UserDto(user,mail,password);
			
			UserRest userRest = userService.createUser(userDto);

			return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.toString(),HttpStatus.OK.getReasonPhrase(), userRest);

		} catch (ControlException e) {			
			
			return new Response<>(HttpStatus.FORBIDDEN.value(), e.getCode(),e.getMessage(), null);
		}
	}
	

	private String getJWTToken(String username){
		String secretKey = "${app.secret-code}" ;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,secretKey.getBytes()).compact();

		return token;
	}
	
	
}