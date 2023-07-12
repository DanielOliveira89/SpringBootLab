package com.oliveira.oliveirawebapp.config;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

	
	@Bean
	public InMemoryUserDetailsManager createUserManager() {
		
		UserDetails userDetails1 = createUser("daniel", "daniel");
		UserDetails userDetails2 = createUser("maria", "maria");
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
		
	}


	private UserDetails createUser(String password, String username) {
		Function<String, String> passwordEncoder
		= input -> passwordEncoder().encode(input);
		
		UserDetails userDetails = User.builder()
										.passwordEncoder(passwordEncoder)
										.username(username)
										.password(password)
										.roles("USER", "ADMIN")
										.build();
		return userDetails;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
