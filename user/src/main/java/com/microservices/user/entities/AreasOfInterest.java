package com.microservices.user.entities;

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
@Table(name="AREAS_OF_INTEREST")
public class AreasOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long areasOfInterestId;

    @Column(name = "AREA_OF_INTEREST_NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "AREA_OF_INTEREST_DESCRIPTION", nullable = false, length = 250)
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}