package com.microservices.course.repositories;

import com.microservices.course.entities.Course;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseNameContainingAndDomainContaining(String courseName, String domain, Sort sort);
}