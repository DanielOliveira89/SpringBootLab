package com.oliveira.rest.restfulapi.resource;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.oliveira.rest.restfulapi.beans.User;
import com.oliveira.rest.restfulapi.dao.UserDaoService;
import com.oliveira.rest.restfulapi.exception.UserNotFoundException;

import jakarta.validation.Valid;

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
	public EntityModel<User> getUser(@PathVariable int id){
		User user = userService.findOne(id);
		
		if (user==null) 
			throw new UserNotFoundException("nothing for this id, tho: "+id);
		
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link = org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo(
									org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User createdUser = userService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdUser.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		userService.deleteById(id);
		
	}
	
	@GetMapping("/user-filtered/{id}")
	public MappingJacksonValue getUserFiltered(@PathVariable int id){
		User user = userService.findOne(id);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "country");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);

		return mappingJacksonValue;
		
	}

}
