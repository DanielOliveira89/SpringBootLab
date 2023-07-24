package com.oliveira.oliveirawebapp.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oliveira.oliveirawebapp.entities.Task;
import com.oliveira.oliveirawebapp.entities.enums.TaskStatus;
import com.oliveira.oliveirawebapp.services.TaskService;

import jakarta.validation.Valid;

//@Controller
//@SessionAttributes("username")
public class TaskController {

	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	@RequestMapping("get-tasks")
	public String getTasks(ModelMap model)
	{
		String username = getLoggedUsername(model);
		List<Task> tasks = taskService.findByUsername(username);
		model.put("tasks", tasks);
		return "allTasks";
		
	}

	private String getLoggedUsername(ModelMap model) {
		Authentication auth =
				SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	@RequestMapping(value="add-task", method=RequestMethod.GET)
	public String showNewTaskPage(ModelMap model)
	{
		String username = getLoggedUsername(model);
		Task newTask = new Task(0, username, "", LocalDate.now().plusYears(1), TaskStatus.IN_PROGRESS);
		model.put("newTask", newTask); 
		return "task";
	}
	 
	@RequestMapping(value="add-task", method=RequestMethod.POST)
	public String addNewTask(ModelMap model, @Valid Task newTask, BindingResult result)
	{
		/*if(result.hasErrors()) {
			return "task";
		}*/ 
		
		String username = getLoggedUsername(model);
		taskService.addTask(username, newTask.getDescription(), newTask.getTargetDate(), TaskStatus.IN_PROGRESS);
		return "redirect:get-tasks";
	}
	
	@RequestMapping("delete-task")
	public String deleteTask(@RequestParam int id)
	{
		taskService.deleteById(id);
		return "redirect:get-tasks";		
	}
	
	@RequestMapping(value="update-task", method=RequestMethod.GET)
	public String showUpdateTaskPage(@RequestParam int id, ModelMap model)
	{
		Task task = taskService.findById(id);
		model.addAttribute("newTask", task);
		return "task";
	}
	
	@RequestMapping(value="update-task", method=RequestMethod.POST)
	public String updateTask(ModelMap model, @Valid Task task, BindingResult result)
	{
		String username = getLoggedUsername(model);
		task.setUsername(username);
		taskService.updateTask(task);
		return "redirect:get-tasks";
	}
	 
}