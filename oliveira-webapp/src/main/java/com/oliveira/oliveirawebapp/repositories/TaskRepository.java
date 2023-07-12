package com.oliveira.oliveirawebapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.oliveirawebapp.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	public List<Task> findByUsername(String username);

}
