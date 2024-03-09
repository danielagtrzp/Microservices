package com.microservices.course.services;

import com.microservices.course.entities.Course;
import com.microservices.course.entities.Student;
import com.microservices.course.repositories.CourseRepository;
import com.microservices.course.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,
                         StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public List<Course> getUserCourses(Long id) {

        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()){
            throw new EntityNotFoundException();
        }
        return student.get().getCourses();
    }
}
