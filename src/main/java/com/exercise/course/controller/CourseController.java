package com.exercise.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

	@GetMapping("/helloWorld")
	public ResponseEntity<String> holaMundo() {
		return new ResponseEntity<String>("Hello world!", HttpStatus.OK);
	}
}
