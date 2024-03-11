package com.microservices.course.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> courseReviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> courseRatings = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "COURSE_STUDENT",
            joinColumns = @JoinColumn(name = "COURSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID")
    )
    private List<Student> students = new ArrayList<>();

    @Column(name = "COURSE_PERFORMANCE")
    private Double performance;
}
