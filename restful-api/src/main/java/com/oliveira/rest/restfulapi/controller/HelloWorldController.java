package com.oliveira.rest.restfulapi.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.rest.restfulapi.beans.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping(path="/hello")
	public String helloWorld() {
		return "Hello, Sir!";
	}
	
	@GetMapping(path="/hello-lang")
	public String helloWorldLang() {
		Locale locale = LocaleContextHolder.getLocale();
		
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
	}
	
	@GetMapping(path="/hello-json")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean ("Hello, Sir!");
	}

	
	@GetMapping(path="/hello/{name}")
	public HelloWorldBean helloWorldByName(@PathVariable String name) {
		return new HelloWorldBean ("Hello," + name + "!");
	}
	
}
