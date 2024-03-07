package com.microservices.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="AREAS_OF_INTEREST")
public class AreasOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areasOfInterestId;

    @Column(name = "AREA_OF_INTEREST_NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "AREA_OF_INTEREST_DESCRIPTION", nullable = false, length = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}