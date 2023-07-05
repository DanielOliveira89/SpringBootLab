package com.oliveira.oliveirawebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oliveira.oliveirawebapp.services.AuthenticationService;

@Controller
@SessionAttributes("username")
public class LoginController {

	private AuthenticationService authService;
	
	
	public LoginController(AuthenticationService authService) {
		super();
		this.authService = authService;
	}
	

	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String gotoHomePage(@RequestParam String username, @RequestParam String password, ModelMap model) {
		
		if(authService.authenticate(username, password)) {
			model.put("username", username); 
			return "home";
		}else {
			model.put("errorMsg", "Invalid Credentials! Please try again.");
			return "login"; 
		}
		
		
	}
	
	
}
