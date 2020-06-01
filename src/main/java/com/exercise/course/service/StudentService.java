package com.exercise.course.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.exercise.course.exception.CourseNotFoundException;
import com.exercise.course.exception.StudentNotFoundException;
import com.exercise.course.model.Student;
import com.exercise.course.model.StudentEntity;

public interface StudentService {
	public Page<StudentEntity> getPaginatedStudents(Integer page, Integer size);

	public List<StudentEntity> getStudents();

	public StudentEntity getStudent(Long id);

	public List<StudentEntity> getStudentsByCourse(Long idCourse);

	public Long saveStudent(Student student, Long courseId) ;

	public Long updateStudentCourse(Long studentId, Long courseId);

	public Long updateStudent(Long studentId, Student student);

	public Long deleteStudent(Long id);
}
