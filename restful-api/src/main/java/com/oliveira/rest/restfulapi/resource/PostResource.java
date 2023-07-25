package com.oliveira.rest.restfulapi.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.rest.restfulapi.beans.Post;
import com.oliveira.rest.restfulapi.repository.PostRepository;
import com.oliveira.rest.restfulapi.repository.UserRepository;

@RestController
public class PostResource {
	
	private PostRepository repository;
	private UserRepository userRepository;
	
	public PostResource(PostRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/post")
	public List<Post> getAllPosts(){
		
		return repository.findAll();
	}
}
