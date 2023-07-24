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
import com.oliveira.oliveirawebapp.repositories.TaskRepository;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("username")
public class TaskControllerJpa {

	public TaskControllerJpa(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}
	
	
	private TaskRepository taskRepository;

	@RequestMapping("get-tasks")
	public String getTasks(ModelMap model)
	{
		String username = getLoggedUsername(model);
		List<Task> tasks = taskRepository.findByUsername(username);
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
		newTask.setUsername(username);
		taskRepository.save(newTask);
		
		//taskService.addTask(username, newTask.getDescription(), newTask.getTargetDate(), newTask.getStatus());
		return "redirect:get-tasks";
	}
	
	@RequestMapping("delete-task")
	public String deleteTask(@RequestParam int id)
	{
		taskRepository.deleteById(id);
		//taskService.deleteById(id);
		return "redirect:get-tasks";		
	}
	
	@RequestMapping(value="update-task", method=RequestMethod.GET)
	public String showUpdateTaskPage(@RequestParam int id, ModelMap model)
	{
		Task task = taskRepository.findById(id).get();
		model.addAttribute("newTask", task);
		return "task";
	}
	
	@RequestMapping(value="update-task", method=RequestMethod.POST)
	public String updateTask(ModelMap model, @Valid Task task, BindingResult result)
	{
		String username = getLoggedUsername(model);
		task.setUsername(username);
		
		taskRepository.save(task);
		//taskService.updateTask(task);
		return "redirect:get-tasks";
	}
	 
}