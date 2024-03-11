package com.microservices.course.services;

import com.microservices.course.dtos.*;
import com.microservices.course.entities.Course;
import com.microservices.course.entities.Rating;
import com.microservices.course.entities.Review;
import com.microservices.course.entities.Student;
import com.microservices.course.mappers.CourseMapper;
import com.microservices.course.repositories.CourseRepository;
import com.microservices.course.repositories.RatingRepository;
import com.microservices.course.repositories.ReviewRepository;
import com.microservices.course.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private  CourseRepository courseRepository;
    @Autowired
    private  StudentRepository studentRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private RatingRepository ratingRepository;


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

    public List<GetCoursesFilteredAndSortedResponse> getCoursesFilteredAndSorted(String courseName, String domain, Sort sort) {
       List<Course> courses = courseRepository.findByCourseNameContainingAndDomainContaining(courseName,domain,sort);
       return CourseMapper.INSTANCE.toGetCoursesFilteredAndSortedResponse(courses);
    }

    public GetCourseByIdResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Courese: " + id + " not found"));
        return CourseMapper.INSTANCE.toGetCourseByIdResponse(course);
    }

    public AddReviewResponse addReview(Long courseId, Long userId, AddReviewRequest addReviewRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found: " + courseId));
        Review review = CourseMapper.INSTANCE.toReview(addReviewRequest);
        review.setUser(userId);
        review.setCourse(course);
        course.getCourseReviews().add(review);
        Review reviewSaved = reviewRepository.save(review);
        courseRepository.save(course);
        return CourseMapper.INSTANCE.toAddReviewResponse(reviewSaved);
    }

    public AddRatingResponse addRating(Long courseId, Long userId, AddRatingRequest addRatingRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found: " + courseId));
        Rating rating = CourseMapper.INSTANCE.toRating(addRatingRequest);
        rating.setUser(userId);
        rating.setCourse(course);
        course.getCourseRatings().add(rating);
        Rating ratingSaved = ratingRepository.save(rating);
        courseRepository.save(course);
        return CourseMapper.INSTANCE.toAddRatingResponse(ratingSaved);
    }

    public GetCourseDetailsByIdResponse getCourseDetailsById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Courese: " + id + " not found"));
        return CourseMapper.INSTANCE.toGetCourseDetailsByIdResponse(course);
    }

    public List<GetCourseByUserIdResponse> getCoursesDetailsByUserId(Long userId) {

        Optional<Student> student = studentRepository.findById(userId);
        if (student.isEmpty()){
            throw new EntityNotFoundException();
        }
        List<Course> courses = student.get().getCourses();
        return CourseMapper.INSTANCE.toGetCourseByUserIdResponse(courses);
    }
}
