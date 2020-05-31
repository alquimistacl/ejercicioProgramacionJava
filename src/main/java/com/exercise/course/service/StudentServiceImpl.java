package com.exercise.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.exercise.course.dao.CourseRepository;
import com.exercise.course.dao.StudentRepository;
import com.exercise.course.model.CourseEntity;
import com.exercise.course.model.Student;
import com.exercise.course.model.StudentEntity;

@Service
public class StudentServiceImpl implements StudentService {

	private static final String STUDENT_ID_NOT_FOUND = "Student id not found";
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Page<StudentEntity> getPaginatedStudents(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);

		return studentRepository.findAll(pageable);
	}

	@Override
	public List<StudentEntity> getStudents() {
		ArrayList<StudentEntity> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);

		return students;
	}

	@Override
	public StudentEntity getStudent(Long id) {
		Optional<StudentEntity> studentFound = studentRepository.findById(id);

		if (!studentFound.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, STUDENT_ID_NOT_FOUND);
		}
		return studentFound.get();
	}

	@Override
	public Long deleteStudent(Long id) {
		getStudent(id);

		studentRepository.deleteById(id);

		return id;
	}

	@Override
	public List<StudentEntity> getStudentsByCourse(Long idCourse) {
		if (!courseRepository.findById(idCourse).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The course id " + idCourse + " was not found");
		}

		List<StudentEntity> studentsFound = studentRepository.findByCourseId(idCourse);

		if (studentsFound.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"The course id " + idCourse + " does not have any student");
		}

		return studentsFound;
	}

	@Override
	public Long saveStudent(Student student, Long courseId) {

		Optional<CourseEntity> courseFound = courseRepository.findById(courseId);

		if (!courseFound.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The course " + courseId + " was not found");
		}

		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setAge(student.getAge());
		studentEntity.setCourse(courseFound.get());
		studentEntity.setLastName(student.getLastName());
		studentEntity.setName(student.getName());
		studentEntity.setRut(student.getRut());

		StudentEntity savedStudent = studentRepository.save(studentEntity);

		return savedStudent.getId();
	}

	@Override
	public Long updateStudent(Long studentId, Student student) {

		Optional<StudentEntity> studentFound = studentRepository.findById(studentId);
		if (!studentFound.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The student " + studentId + " was not found");
		}

		StudentEntity studentEntity = studentFound.get();
		studentEntity.setAge(student.getAge());
		studentEntity.setLastName(student.getLastName());
		studentEntity.setName(student.getName());
		studentEntity.setRut(student.getRut());

		StudentEntity savedStudentEntity = studentRepository.save(studentEntity);

		return savedStudentEntity.getId();
	}

	@Override
	public Long updateStudentCourse(Long studentId, Long courseId) {
		Optional<StudentEntity> studentFound = studentRepository.findById(studentId);
		if (studentFound.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The student " + studentId + " was not found");
		}

		Optional<CourseEntity> courseFound = courseRepository.findById(courseId);

		if (courseFound.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The course " + courseId + " was not found");
		}

		StudentEntity studentEntityToBeModified = studentFound.get();
		studentEntityToBeModified.setCourse(courseFound.get());

		studentRepository.save(studentEntityToBeModified);

		return studentId;
	}

}
