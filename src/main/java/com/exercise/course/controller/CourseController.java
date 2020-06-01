package com.exercise.course.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.course.model.Course;
import com.exercise.course.model.CourseEntity;
import com.exercise.course.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation."),
		@ApiResponse(code = 400, message = "Bad Request."), @ApiResponse(code = 401, message = "Unauthorized."),
		@ApiResponse(code = 403, message = "Forbidden."), @ApiResponse(code = 404, message = "Not Found."),
		@ApiResponse(code = 500, message = "Internal Server Error."),
		@ApiResponse(code = 200, message = "Unexpected error.", response = Error.class) })
@Api(value = "course")
@RestController
public class CourseController {

	private static final String COURSE_ID = "Course id ";
	@Autowired
	private CourseService courseService;

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to get paginated courses.", nickname = "getPaginatedCourses", tags = {
			"course-controller", })
	@GetMapping(path = "/courses", params = { "page", "size" }, produces = { "application/json" })
	public ResponseEntity<Page<CourseEntity>> getPaginatedCourses(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		Page<CourseEntity> paginatedCourses = courseService.getPaginatedCourses(page, size);
		return new ResponseEntity<>(paginatedCourses, HttpStatus.OK);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to get all courses.", nickname = "getCourses", tags = { "course-controller", })
	@GetMapping(path = "/courses/all", produces = { "application/json" })
	public ResponseEntity<List<CourseEntity>> getCourses() {

		List<CourseEntity> courses = courseService.getCourses();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to get a course by id.", nickname = "getCourse", tags = { "course-controller", })
	@GetMapping(path = "/courses/{id}", produces = { "application/json" })
	public ResponseEntity<Course> getCourse(@Valid @PathVariable("id") Long id) {
		Course course = courseService.getCourse(id);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to create a course.", nickname = "createCourse", tags = { "course-controller", })
	@PostMapping(path = "/courses", produces = { "text/plain" }, consumes = { "application/json" })
	public ResponseEntity<String> createCourse(@Valid @RequestBody Course course) {
		Long savedCourseId = courseService.saveCourse(course);
		return new ResponseEntity<>(COURSE_ID + savedCourseId + " correctly saved", HttpStatus.CREATED);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to update a course.", nickname = "updateCourse", tags = { "course-controller", })
	@PutMapping(path = "/courses/{id}", produces = { "text/plain" }, consumes = { "application/json" })
	public ResponseEntity<String> updateCourse(@Valid @PathVariable("id") Long id, @Valid @RequestBody Course course) {
		Long savedCourseId = courseService.updateCourse(id, course);
		return new ResponseEntity<>(COURSE_ID + savedCourseId + " correctly updated", HttpStatus.OK);

	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to delete a course by id.", nickname = "deleteCourse", tags = {
			"course-controller", })
	@DeleteMapping(path="/courses/{id}", produces = { "text/plain" })
	public ResponseEntity<String> deleteCourse(@Valid @PathVariable("id") Long id) {
		Long idDeletedCourse = courseService.deleteCourse(id);
		return new ResponseEntity<>(COURSE_ID + idDeletedCourse + " correctly deleted", HttpStatus.OK);
	}

}
