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

import com.exercise.course.model.Student;
import com.exercise.course.model.StudentEntity;
import com.exercise.course.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller to handle the student mappings
 * @author Luis San Martin
 *
 */
@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation."),
		@ApiResponse(code = 400, message = "Bad Request."), @ApiResponse(code = 401, message = "Unauthorized."),
		@ApiResponse(code = 403, message = "Forbidden."), @ApiResponse(code = 404, message = "Not Found."),
		@ApiResponse(code = 500, message = "Internal Server Error."),
		@ApiResponse(code = 200, message = "Unexpected error.", response = Error.class) })
@Api(value = "student")
@RestController
public class StudentController {

	private static final String STUDENT_ID = "Student id ";
	@Autowired
	private StudentService studentService;

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to get paginated students.", nickname = "getPaginatedStudents", tags = {
			"student-controller", })
	@GetMapping(path = "/students", params = { "page", "size" }, produces = { "application/json" })
	public ResponseEntity<Page<StudentEntity>> getPaginatedStudents(

			@Valid @RequestParam("page") Integer page, @Valid @RequestParam("size") Integer size) {

		Page<StudentEntity> paginatedStudents = studentService.getPaginatedStudents(page, size);

		return new ResponseEntity<>(paginatedStudents, HttpStatus.OK);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to get all students.", nickname = "getStudents", tags = { "student-controller", })
	@GetMapping(path = "/students/all", produces = { "application/json" })
	public ResponseEntity<List<StudentEntity>> getStudents() {
		List<StudentEntity> students = studentService.getStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to get a student by id.", nickname = "getStudent", tags = {
			"student-controller", })
	@GetMapping(path = "/students/{id}", produces = { "application/json" })
	public ResponseEntity<StudentEntity> getStudent(@Valid @PathVariable("id") Long id) {
		StudentEntity student = studentService.getStudent(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to get students by course id.", nickname = "getStudentsByCourse", tags = {
			"student-controller", })
	@GetMapping(path = "/course/{id}/students", produces = { "application/json" })
	public ResponseEntity<List<StudentEntity>> getStudentsByCourse(

			@Valid @PathVariable("id") Long courseId) {
		List<StudentEntity> studentsByCourse = studentService.getStudentsByCourse(courseId);
		return new ResponseEntity<>(studentsByCourse, HttpStatus.OK);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to create a student with it course id.", nickname = "createStudent", tags = {
			"student-controller", })
	@PostMapping(path = "/course/{courseId}/students", produces = { "application/json", "text/plain" }, consumes = {
			"application/json" })
	public ResponseEntity<String> createStudent(@Valid @PathVariable("courseId") Long courseId,
			@Valid @RequestBody Student student) {
		Long saveStudent = studentService.saveStudent(student, courseId);

		return new ResponseEntity<>(STUDENT_ID + saveStudent + " was created", HttpStatus.CREATED);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to update a student course with course id and student id.", nickname = "updateStudentCourse", tags = {
			"student-controller", })
	@PutMapping(path = "/course/{courseId}/students/{studentId}", produces = { "application/json", "text/plain" }, consumes = {
			"application/json" })
	public ResponseEntity<String> updateStudentCourse(

			@Valid @PathVariable("courseId") Long courseId, @Valid @PathVariable("studentId") Long studentId) {
		Long updateStudentCourse = studentService.updateStudentCourse(studentId, courseId);

		return new ResponseEntity<>(STUDENT_ID + studentId + " was updated with course id " + updateStudentCourse,
				HttpStatus.CREATED);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to update a student.", nickname = "updateStudent", tags = {
			"student-controller", })
	@PutMapping(path = "/students/{studentId}", produces = { "application/json", "text/plain" }, consumes = { "application/json" })
	public ResponseEntity<String> updateStudent(@Valid @PathVariable("studentId") Long studentId,
			@Valid @RequestBody Student student) {
		Long updateStudent = studentService.updateStudent(studentId, student);

		return new ResponseEntity<>(STUDENT_ID + updateStudent + " was updated", HttpStatus.CREATED);
	}

	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@ApiOperation(value = "Operation to delete a student by id.", nickname = "deleteStudent", tags = {
			"student-controller", })
	@DeleteMapping(path = "/students/{studentId}", produces = { "application/json", "text/plain" })
	public ResponseEntity<String> deleteStudent(@Valid @PathVariable("studentId") Long studentId) {
		Long deletedStudent = studentService.deleteStudent(studentId);

		return new ResponseEntity<>(STUDENT_ID + deletedStudent + " was deleted", HttpStatus.OK);
	}
}
