package com.exercise.course.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.exercise.course.model.StudentEntity;

public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long> {

	List<StudentEntity> findByCourseId(Long courseId);
}
