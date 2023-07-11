package com.oliveira.oliveirawebapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.oliveira.oliveirawebapp.entities.Task;
import com.oliveira.oliveirawebapp.entities.enums.TaskStatus;

import jakarta.validation.Valid;

@Service
public class TaskService {

	private static int tasksCount = 0;
	
	private static List<Task> tasks = new ArrayList<Task>();
	static {
		tasks.add(new Task(++tasksCount, "Daniel", "Learn SpringBoot", LocalDate.now().plusMonths(1), TaskStatus.NOT_DONE));
		tasks.add(new Task(++tasksCount, "Daniel", "Learn GCP", LocalDate.now().plusMonths(2), TaskStatus.DONE));
		tasks.add(new Task(++tasksCount, "Maria", "Learn AWS", LocalDate.now().plusMonths(3), TaskStatus.NOT_DONE));
		tasks.add(new Task(++tasksCount, "Maria", "Learn Azure", LocalDate.now().plusMonths(3), TaskStatus.DONE));
	}
	
	public List<Task> findByUsername(String username){
		Predicate<? super Task> predicate = task -> task.getUsername().equalsIgnoreCase(username);
		return tasks.stream().filter(predicate).toList();
	}
	
	public void addTask(String username, String description, LocalDate targetDate, TaskStatus status) {
		Task task = new Task(++tasksCount, username, description, targetDate, status);
		tasks.add(task);
		
	}
	
	public void deleteById(int id){
		Predicate<? super Task> predicate = task -> task.getId() == id;
		tasks.removeIf(predicate);
	}

	public Task findById(int id) {
		
		Predicate<? super Task> predicate = task -> task.getId() == id;
		Task task = tasks.stream().filter(predicate).findFirst().get();
		
		return task;
	}

	public void updateTask(@Valid Task task) {
		deleteById(task.getId());
		tasks.add(task);
		
	}

}
