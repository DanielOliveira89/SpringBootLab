package com.oliveira.oliveirawebapp.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliveira.oliveirawebapp.entities.Course;


@RestController
public class CourseController {

	@RequestMapping("/courses")
	public List<Course> getAllCourses(){
		return Arrays.asList(
				new Course(1, "Learn AWS", "in20minutes"),
				new Course(2, "Learn DevOps", "in20minutes"),
				new Course(3, "Learn Azure", "in20minutes")
				);
				
	}

}
