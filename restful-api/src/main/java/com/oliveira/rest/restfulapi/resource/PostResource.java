package com.oliveira.rest.restfulapi.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.rest.restfulapi.beans.Post;
import com.oliveira.rest.restfulapi.repository.PostRepository;

@RestController
public class PostResource {
	
	private PostRepository repository;
	
	public PostResource(PostRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/post")
	public List<Post> getAllPosts(){
		
		return repository.findAll();
	}
}
