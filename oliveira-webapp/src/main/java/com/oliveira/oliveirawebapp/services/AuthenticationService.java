package com.oliveira.oliveirawebapp.services;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean authenticate(String username, String password) {
		
		boolean isValidUsername = username.equalsIgnoreCase("daniel");
		boolean isValidPassword = password.equalsIgnoreCase("qwerty123");
		
		
		return isValidUsername && isValidPassword;
	};

}
