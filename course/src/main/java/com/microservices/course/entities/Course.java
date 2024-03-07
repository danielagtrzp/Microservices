package com.microservices.course.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(name = "COURSE_NAME", nullable = false, length = 50, unique = true)
    private String courseName;

    @Column(name = "COURSE_DOMAIN", length = 50)
    private String domain;

    @Column(name = "COURSE_DESCRIPTION", nullable = false, length = 250)
    private String courseDescription;

    @Column(name = "COURSE_AUTHOR", nullable = false, length = 50)
    private String courseAuthor;

    @Column(name = "COURSE_PRICE", nullable = false)
    private Double coursePrice;

    @Column(name = "COURSE_DURATION", nullable = false)
    private Double courseDuration;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reviews> courseReviews;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> courseRatings;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

}
