package com.oliveira.oliveirawebapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oliveira.oliveirawebapp.entities.Task;
import com.oliveira.oliveirawebapp.entities.enums.TaskStatus;

@Service
public class TaskService {

	private static int tasksCount = 0;
	
	private static List<Task> tasks = new ArrayList();
	static {
		tasks.add(new Task(++tasksCount, "Daniel", "Learn SpringBoot", LocalDate.now().plusMonths(1), TaskStatus.NOT_DONE));
		tasks.add(new Task(++tasksCount, "Daniel", "Learn GCP", LocalDate.now().plusMonths(2), TaskStatus.DONE));
		tasks.add(new Task(++tasksCount, "Maria", "Learn AWS", LocalDate.now().plusMonths(3), TaskStatus.NOT_DONE));
		tasks.add(new Task(++tasksCount, "Maria", "Learn Azure", LocalDate.now().plusMonths(3), TaskStatus.DONE));
	}
	
	public List<Task> findByUsername(String username){
		return tasks;
	}
	
	public void addTask(String username, String description, LocalDate targetDate, TaskStatus status) {
		Task task = new Task(++tasksCount, username, description, targetDate, status);
		tasks.add(task);
		
	}

}
