package com.exercise.course.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.course.model.Course;
import com.exercise.course.model.CourseEntity;
import com.exercise.course.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping(params = { "page", "size" }, path = "/courses")
	public ResponseEntity<Page<CourseEntity>> getPaginatedCourses(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		Page<CourseEntity> paginatedCourses = courseService.getPaginatedCourses(page, size);
		return new ResponseEntity<>(paginatedCourses, HttpStatus.OK);
	}

	@GetMapping("/courses/all")
	public ResponseEntity<List<CourseEntity>> getCourses() {

		List<CourseEntity> courses = courseService.getCourses();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourse(@PathParam("id") Long id) {
		Course course = courseService.getCourse(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@PostMapping("/courses")
	public ResponseEntity<String> saveCourse(@RequestBody Course course) {
		Long savedCourseId = courseService.saveCourse(course);
		return new ResponseEntity<>("Course id " + savedCourseId + " saved correctly", HttpStatus.CREATED);
	}

	@PutMapping("/courses/{id}")
	public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathParam("id") Long id) {
		Long savedCourseId = courseService.updateCourse(id, course);
		return new ResponseEntity<>("Course id " + savedCourseId + " correctly updated", HttpStatus.OK);
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<String> deleteCourse(@PathParam("id") Long id) {
		Long idDeletedCourse = courseService.deleteCourse(id);
		return new ResponseEntity<>("Course id " + idDeletedCourse + " correctly deleted", HttpStatus.OK);
	}

}
