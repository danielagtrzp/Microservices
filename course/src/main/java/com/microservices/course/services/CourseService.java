package com.microservices.course.services;

import com.microservices.course.dtos.AddCourseRequest;
import com.microservices.course.dtos.AddCourseResponse;
import com.microservices.course.dtos.GetUserCoursesResponse;
import com.microservices.course.entities.Course;
import com.microservices.course.entities.Student;
import com.microservices.course.mappers.CourseMapper;
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
    private  CourseRepository courseRepository;
    @Autowired
    private  StudentRepository studentRepository;


    public List<GetUserCoursesResponse> getUserCourses(Long id) {

        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()){
            throw new EntityNotFoundException();
        }
        List<Course> courses = student.get().getCourses();
        return CourseMapper.INSTANCE.toGetUserCoursesResponse(courses);
    }

    public AddCourseResponse addCourse(AddCourseRequest addCourseRequest) {
        Course course = CourseMapper.INSTANCE.toCourse(addCourseRequest);
        Course courseSaved = courseRepository.save(course);
        return CourseMapper.INSTANCE.toCourseResponse(courseSaved);
    }

    public void deleteCourse(Long id) {
        courseRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Courese: " + id + " not found"));
        courseRepository.deleteById(id);
    }
}
