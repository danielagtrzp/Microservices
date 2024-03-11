package com.microservices.course.services;

import com.microservices.course.dtos.*;
import com.microservices.course.entities.Course;
import com.microservices.course.entities.Rating;
import com.microservices.course.entities.Review;
import com.microservices.course.entities.Student;
import com.microservices.course.exceptions.GeneralCustomException;
import com.microservices.course.repositories.CourseRepository;
import com.microservices.course.repositories.RatingRepository;
import com.microservices.course.repositories.ReviewRepository;
import com.microservices.course.repositories.StudentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    CourseRepository courseRepository;

    @Mock
    StudentRepository studentRepository;

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    RatingRepository ratingRepository;

    @InjectMocks
    CourseService courseService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getUserCourses() {
        Student student = new Student();
        given(studentRepository.findById(anyLong())).willReturn(Optional.of(student));

        List<GetUserCoursesResponse> finalCourses = courseService.getUserCourses(anyLong());

        verify(studentRepository).findById(anyLong());
    }

    @Test
    void getUserCourses_StudentDoesNotExist() {

        given(studentRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> courseService.getUserCourses(1L));
    }

    @Test
    void addCourse() {
        AddCourseRequest addCourseRequest = new AddCourseRequest();
        Course course = new Course();
        given(courseRepository.save(any())).willReturn(course);

        courseService.addCourse(addCourseRequest);

        verify(courseRepository).save(any());
    }

    @Test
    void addCourse_CourseAlreadyExist() {
        AddCourseRequest addCourseRequest = new AddCourseRequest();
        Course course = new Course();
        given(courseRepository.save(any())).willThrow(EntityExistsException.class);

        assertThrows(EntityExistsException.class, () -> courseService.addCourse(addCourseRequest));
    }

    @Test
    void deleteCourse() {

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(new Course()));
        doNothing().when(courseRepository).deleteById(anyLong());

        courseService.deleteCourse(anyLong());

        verify(courseRepository).findById(anyLong());
        verify(courseRepository).deleteById(anyLong());

    }

    @Test
    void deleteCourse_CourseNotFound() {
        given(courseRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,()-> courseService.deleteCourse(anyLong()));

    }

    @Test
    void getCoursesFilteredAndSorted() {
        List<Course> courses = List.of(
                new Course(),
                new Course()
        );
        Sort sort = Sort.by(Sort.Direction.DESC, "coursePrice");
        given(courseRepository.findByCourseNameContainingAndDomainContaining(any(),any(), eq(sort))).willReturn(courses);

        List<GetCoursesFilteredAndSortedResponse> actualCourses = courseService.getCoursesFilteredAndSorted(null, null, sort);

        verify(courseRepository).findByCourseNameContainingAndDomainContaining(any(), any(), eq(sort));

    }

    @Test
    void addReview() {
        Course course = new Course();
        AddReviewRequest addReviewRequest = new AddReviewRequest();
        Review savedReview = new Review();

        given(courseRepository.findById(anyLong())).willReturn(Optional.of(course));
        given(reviewRepository.save(any())).willReturn(savedReview);



        AddReviewResponse result = courseService.addReview(1L, 1L, addReviewRequest);

        verify(courseRepository).findById(anyLong());
        verify(reviewRepository).save(any());
        verify(courseRepository).save(any());

    }

    @Test
    void addRating() {
        Course course = new Course();
        AddRatingRequest addRatingRequest = new AddRatingRequest();
        Rating savedRating = new Rating();

        given(courseRepository.findById(anyLong())).willReturn(Optional.of(course));
        given(ratingRepository.save(any())).willReturn(savedRating);



        AddRatingResponse result = courseService.addRating(1L, 1L, addRatingRequest);

        verify(courseRepository).findById(anyLong());
        verify(ratingRepository).save(any());
        verify(courseRepository).save(any());
    }

    @Test
    void getCourseById() {
        Optional<Course> course= Optional.of(new Course());
        given(courseRepository.findById(anyLong())).willReturn(course);

        courseService.getCourseById(1L);

        verify(courseRepository).findById(anyLong());
    }

    @Test
    void getCourseDetailsById() {
        Optional<Course> course= Optional.of(new Course());
        given(courseRepository.findById(anyLong())).willReturn(course);

        courseService.getCourseDetailsById(1L);

        verify(courseRepository).findById(anyLong());
    }

    @Test
    void getCoursesDetailsByUserId() {
        Student student = new Student();
        given(studentRepository.findById(anyLong())).willReturn(Optional.of(student));

        List<GetCourseByUserIdResponse> finalCourses = courseService.getCoursesDetailsByUserId(anyLong());

        verify(studentRepository).findById(anyLong());
    }

    @Test
    void getCoursesSortedByPerformance() {
        given(courseRepository.findAll(any(Sort.class))).willReturn(List.of());
        courseService.getCoursesSortedByPerformance(Sort.by("some"));
        verify(courseRepository).findAll(any(Sort.class));
    }

    @Test
    void updatePerformance() {
        List<Review> reviews = List.of(new Review(1L,"some",1L,new Course()));
        List<Rating> ratings = List.of(new Rating(1L,10.0,1L,new Course()));
        List<Student> students = List.of(new Student(1L,"student","student","student",List.of()));
        Course course = new Course(1L,"some","some","some","some",1.0,1.0,reviews,ratings,students,10.0);
        given(courseRepository.findById(anyLong())).willReturn(Optional.of(course));
        given(courseRepository.save(any())).willReturn(course);

        UpdatePerformanceResponse response = courseService.updatePerformance(1L);

        verify(courseRepository).findById(anyLong());
        verify(courseRepository).save(any());

    }
}