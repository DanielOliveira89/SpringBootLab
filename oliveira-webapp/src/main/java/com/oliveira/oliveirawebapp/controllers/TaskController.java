package com.oliveira.oliveirawebapp.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oliveira.oliveirawebapp.entities.Task;
import com.oliveira.oliveirawebapp.entities.enums.TaskStatus;
import com.oliveira.oliveirawebapp.services.TaskService;

@Controller
@SessionAttributes("username")
public class TaskController {

	private TaskService taskService;
	
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	@RequestMapping("get-tasks")
	public String getTasks(ModelMap model)
	{
		List<Task> tasks = taskService.findByUsername("Daniel");
		model.put("tasks", tasks);
		return "allTasks";
		
	}
	
	@RequestMapping(value="add-task", method=RequestMethod.GET)
	public String showNewTaskPage(ModelMap model)
	{
		String username = (String)model.get("username");
		Task task = new Task(0, username, "", LocalDate.now().plusYears(1), TaskStatus.NOT_DONE);
		model.put("task", task);
		return "task";
	}
	 
	@RequestMapping(value="add-task", method=RequestMethod.POST)
	public String addNewTask(ModelMap model, Task task)
	{
		String username = (String)model.get("username");
		taskService.addTask(username, task.getDescription(), LocalDate.now().plusYears(1), TaskStatus.NOT_DONE);
		return "redirect:get-tasks";
	} 
	 
}
