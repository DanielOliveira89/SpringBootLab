package com.oliveira.rest.restfulapi.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.rest.restfulapi.beans.User;
import com.oliveira.rest.restfulapi.dao.UserDaoService;

@RestController
public class UserResource {
	
	private UserDaoService userService;
	public UserResource(UserDaoService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id){
		
		return userService.findOne(id);
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user){
		
		return userService.save(user);
	}

}