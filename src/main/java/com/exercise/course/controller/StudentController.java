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

import com.exercise.course.model.Student;
import com.exercise.course.model.StudentEntity;
import com.exercise.course.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(params = { "page", "size" }, path = "/students")
	public ResponseEntity<Page<StudentEntity>> getPaginatedStudents(@RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {

		Page<StudentEntity> paginatedStudents = studentService.getPaginatedStudents(page, size);

		return new ResponseEntity<>(paginatedStudents, HttpStatus.OK);
	}

	@GetMapping("/students/all")
	public ResponseEntity<List<StudentEntity>> getStudents() {
		List<StudentEntity> students = studentService.getStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<StudentEntity> getStudent(@PathParam("id") Long id) {
		StudentEntity student = studentService.getStudent(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/course/{id}/students")
	public ResponseEntity<List<StudentEntity>> getStudentsByCourse(@PathParam("id") Long courseId) {
		List<StudentEntity> studentsByCourse = studentService.getStudentsByCourse(courseId);
		return new ResponseEntity<>(studentsByCourse, HttpStatus.OK);
	}

	@PostMapping("/course/{courseId}/students")
	public ResponseEntity<String> saveStudent(@PathParam("courseId") Long courseId, @RequestBody Student student) {
		Long saveStudent = studentService.saveStudent(student, courseId);

		return new ResponseEntity<>("Student id " + saveStudent + " was created", HttpStatus.CREATED);
	}

	@PutMapping("/course/{courseId}/students/{studentId}")
	public ResponseEntity<String> updateStudentCourse(@PathParam("courseId") Long courseId,
			@PathParam("studentId") Long studentId) {
		Long updateStudentCourse = studentService.updateStudentCourse(studentId, courseId);

		return new ResponseEntity<>("Student id " + studentId + " was updated with course id " + updateStudentCourse,
				HttpStatus.CREATED);
	}

	@PutMapping("/students/{studentId}")
	public ResponseEntity<String> updateStudentCourse(@PathParam("studentId") Long studentId,
			@RequestBody Student student) {
		Long updateStudent = studentService.updateStudent(studentId, student);

		return new ResponseEntity<>("Student id " + updateStudent + " was updated", HttpStatus.CREATED);
	}

	@DeleteMapping("/students/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathParam("studentId") Long studentId) {
		Long deletedStudent = studentService.deleteStudent(studentId);

		return new ResponseEntity<>("Student id " + deletedStudent + " was deleted", HttpStatus.OK);
	}
}
