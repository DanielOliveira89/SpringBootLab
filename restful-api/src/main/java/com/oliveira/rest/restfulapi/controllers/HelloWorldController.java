package com.oliveira.rest.restfulapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.rest.restfulapi.beans.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@GetMapping(path="/hello")
	public String helloWorld() {
		return "Hello, Sir!";
	}
	
	@GetMapping(path="/hello-json")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean ("Hello, Sir!");
	}

}
