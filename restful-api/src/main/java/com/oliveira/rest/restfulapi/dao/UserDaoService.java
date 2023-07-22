package com.oliveira.rest.restfulapi.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.oliveira.rest.restfulapi.beans.User;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "Adam", LocalDate.now().minusYears(30), "Male", "1st Street, NY", "USA"));
		users.add(new User(2, "Beth", LocalDate.now().minusYears(20), "Female", "Av. Republica, Lisboa", "Portugal"));
		users.add(new User(3, "Charles", LocalDate.now().minusYears(25), "Male", "Av. Paulista, SP", "Brazil"));
		users.add(new User(4, "Denise", LocalDate.now().minusYears(32), "Female", "Las Rambas, Barcelona", "Spain"));
	}

	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		User user = users.stream().filter(predicate).findFirst().orElse(null);
		
		return user;
		
	}
	
	public User save(User user) {
		user.setId(users.size()+1);
		users.add(user);
		return user;
	}
	
	public void deleteById(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
		
		
	}
}
