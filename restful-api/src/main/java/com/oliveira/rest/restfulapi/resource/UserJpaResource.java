package com.oliveira.rest.restfulapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.oliveira.rest.restfulapi.beans.Post;
import com.oliveira.rest.restfulapi.beans.User;
import com.oliveira.rest.restfulapi.dao.UserDaoService;
import com.oliveira.rest.restfulapi.exception.UserNotFoundException;
import com.oliveira.rest.restfulapi.repository.PostRepository;
import com.oliveira.rest.restfulapi.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	private UserDaoService userService;
	private UserRepository userRepo;
	private PostRepository postRepo;
	
	public UserJpaResource(UserDaoService userService, UserRepository userRepo, PostRepository postRepo) {
		this.userService = userService;
		this.userRepo = userRepo;
		this.postRepo = postRepo;
	}
	
	
	@GetMapping("/user")
	public List<User> getAllUsers(){
		
		return userRepo.findAll();
	}
	
	@GetMapping("/user/{id}")
	public EntityModel<User> getUser(@PathVariable int id){
		Optional<User> user = userRepo.findById(id);
		
		if (user.isEmpty()) 
			throw new UserNotFoundException("nothing for this id: "+id);
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link = org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo(
									org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User createdUser = userRepo.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdUser.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id){
		userRepo.deleteById(id);
		
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
	
	@GetMapping("/user/{id}/posts")
	public List<Post> getUserPosts(@PathVariable int id){
		Optional<User> user = userRepo.findById(id);
		
		if (user.isEmpty()) 
			throw new UserNotFoundException("nothing for this id: "+id);
		
		return user.get().getPosts();
		
		
	}
	
	@PostMapping("/user/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
		Optional<User> user = userRepo.findById(id);
		
		if (user.isEmpty()) 
			throw new UserNotFoundException("no user id: "+id);
		
		post.setUser(user.get());
		Post savedPost = postRepo.save(post);
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
				return ResponseEntity.created(location).build();
		
		
	}

}
