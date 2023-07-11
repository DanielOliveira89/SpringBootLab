package com.oliveira.oliveirawebapp.controllers;

import java.time.LocalDate;
import java.util.List;

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
		Task newTask = new Task(0, username, "", LocalDate.now().plusYears(1), TaskStatus.NOT_DONE);
		model.put("newTask", newTask); 
		return "task";
	}
	 
	@RequestMapping(value="add-task", method=RequestMethod.POST)
	public String addNewTask(ModelMap model, @Valid Task newTask, BindingResult result)
	{
		/*if(result.hasErrors()) {
			return "task";
		}*/ 
		
		String username = (String)model.get("username");
		taskService.addTask(username, newTask.getDescription(), newTask.getTargetDate(), TaskStatus.NOT_DONE);
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
		String username = (String)model.get("username");
		task.setUsername(username);
		taskService.updateTask(task);
		return "redirect:get-tasks";
	}
	 
}