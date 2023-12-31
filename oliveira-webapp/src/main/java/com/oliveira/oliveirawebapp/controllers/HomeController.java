package com.oliveira.oliveirawebapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class HomeController {


	@RequestMapping(value="/", method = RequestMethod.GET)
	public String gotoHome(ModelMap model) {
		model.put("username", getLoggedUsername());
		return "home";
	}
	
	private String getLoggedUsername() {
		Authentication auth =
				SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}
