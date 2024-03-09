package com.microservices.course.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter


@Entity
@Table(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String userName;

    private String firstName;

    private String lastName;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();
}
