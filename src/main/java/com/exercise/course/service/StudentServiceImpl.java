package com.exercise.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exercise.course.dao.CourseRepository;
import com.exercise.course.dao.StudentRepository;
import com.exercise.course.exception.CourseDoesNotHaveStudentsException;
import com.exercise.course.exception.CourseNotFoundException;
import com.exercise.course.exception.StudentNotFoundException;
import com.exercise.course.model.CourseEntity;
import com.exercise.course.model.Student;
import com.exercise.course.model.StudentEntity;

/**
 * Service in charge of student operations
 * @author Luis San Martin
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

	private static final String WAS_NOT_FOUND = " was not found";
	private static final String STUDENT_ID_NOT_FOUND = "Student id not found";
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	
	/**
	 * allows to get students by page
	 */
	@Override
	public Page<StudentEntity> getPaginatedStudents(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);

		return studentRepository.findAll(pageable);
	}

	/**
	 * allows to get all students
	 */
	@Override
	public List<StudentEntity> getStudents() {
		ArrayList<StudentEntity> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);

		return students;
	}

	/**
	 * allows to get a student by id
	 */
	@Override
	public StudentEntity getStudent(Long id) {
		Optional<StudentEntity> studentFound = studentRepository.findById(id);

		if (!studentFound.isPresent()) {
			throw new StudentNotFoundException(STUDENT_ID_NOT_FOUND);
		}
		return studentFound.get();
	}

	/**
	 * allows to delete a student by id
	 */
	@Override
	public Long deleteStudent(Long id) {
		getStudent(id);

		studentRepository.deleteById(id);

		return id;
	}

	/**
	 * allows to get all students in a course
	 */
	@Override
	public List<StudentEntity> getStudentsByCourse(Long idCourse) {
		if (!courseRepository.findById(idCourse).isPresent()) {
			String reason = "The course id " + idCourse + WAS_NOT_FOUND;
			throw new CourseNotFoundException(reason);
		}

		List<StudentEntity> studentsFound = studentRepository.findByCourseId(idCourse);

		if (studentsFound.isEmpty()) {

			String reason = "The course id " + idCourse + " has no studentss";
			throw new CourseDoesNotHaveStudentsException(reason);
		}

		return studentsFound;
	}

	/**
	 * allows to save a new student
	 */
	@Override
	public Long saveStudent(Student student, Long courseId) {

		Optional<CourseEntity> courseFound = courseRepository.findById(courseId);

		if (!courseFound.isPresent()) {
			String reason = "The course " + courseId + WAS_NOT_FOUND;
			throw new CourseNotFoundException(reason);
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

	/**
	 * allows to update a student by id
	 */
	@Override
	public Long updateStudent(Long studentId, Student student) {

		Optional<StudentEntity> studentFound = studentRepository.findById(studentId);
		if (!studentFound.isPresent()) {
			String reason = "The student " + studentId + WAS_NOT_FOUND;
			throw new StudentNotFoundException(reason);
		}

		StudentEntity studentEntity = studentFound.get();
		studentEntity.setAge(student.getAge());
		studentEntity.setLastName(student.getLastName());
		studentEntity.setName(student.getName());
		studentEntity.setRut(student.getRut());

		StudentEntity savedStudentEntity = studentRepository.save(studentEntity);

		return savedStudentEntity.getId();
	}

	/**
	 * allows to update the course of the student
	 */
	@Override
	public Long updateStudentCourse(Long studentId, Long courseId) {
		Optional<StudentEntity> studentFound = studentRepository.findById(studentId);
		if (studentFound.isEmpty()) {
			String reason = "The student " + studentId + WAS_NOT_FOUND;
			throw new StudentNotFoundException(reason);
		}

		Optional<CourseEntity> courseFound = courseRepository.findById(courseId);

		if (courseFound.isEmpty()) {
			String reason = "The course " + courseId + WAS_NOT_FOUND;
			throw new CourseNotFoundException(reason);
		}

		StudentEntity studentEntityToBeModified = studentFound.get();
		studentEntityToBeModified.setCourse(courseFound.get());

		studentRepository.save(studentEntityToBeModified);

		return studentId;
	}

}
