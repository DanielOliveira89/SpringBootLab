package com.oliveira.rest.restfulapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oliveira.rest.restfulapi.beans.User;
import com.oliveira.rest.restfulapi.dao.UserDaoService;
import com.oliveira.rest.restfulapi.exceptions.UserNotFoundException;

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
		User user = userService.findOne(id);
		
		if (user==null) 
			throw new UserNotFoundException("nothing for this id, tho: "+id);
		
		return user;
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User createdUser = userService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdUser.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}

}
