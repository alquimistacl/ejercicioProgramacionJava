package com.exercise.course.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.exercise.course.model.StudentEntity;

/**
 * Repository to handle the student db calls
 * @author Luis San Martin
 *
 */
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {

	List<StudentEntity> findByCourseId(Long courseId);
}
